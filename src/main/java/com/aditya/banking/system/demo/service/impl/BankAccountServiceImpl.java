package com.aditya.banking.system.demo.service.impl;

import com.aditya.banking.system.demo.dao.*;
import com.aditya.banking.system.demo.entity.constant.enums.BankAccountStatus;
import com.aditya.banking.system.demo.entity.constant.enums.ResponseCode;
import com.aditya.banking.system.demo.entity.dao.BankAccount;
import com.aditya.banking.system.demo.entity.dao.BankAccountTransaction;
import com.aditya.banking.system.demo.entity.dao.CustomerAccount;
import com.aditya.banking.system.demo.exception.BusinessLogicException;
import com.aditya.banking.system.demo.service.api.BankAccountService;
import com.aditya.banking.system.demo.utils.MappingUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private static final Logger LOG = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerAccountRepository customerAccountRepository;

    @Autowired
    BankAccountTransactionRepository bankAccountTransactionRepository;

    @Autowired
    BranchRepository branchRepository;

    @Autowired
    MappingUtils mappingUtils;

    @Override
    @Transactional
    public BankAccount createBankAccount(Long customerId, BankAccount bankAccount) {
        if (customerRepository.existsById(customerId) && branchRepository.existsById(bankAccount.getBankBranchId())) {
            try {
                bankAccount.setCreatedBy(customerId);
                bankAccount.setCreatedDate(new Date());
                bankAccount.setUpdatedBy(customerId);
                bankAccount.setUpdatedDate(new Date());
                BankAccount savedBankAccount = doTransaction(bankAccount, bankAccount.getWithdrawalAmount(), bankAccount.getDepositAmount());
                CustomerAccount customerAccount = mappingUtils.getCustomerAccountEntity(savedBankAccount, customerId);
                customerAccountRepository.save(customerAccount);
                return savedBankAccount;
            } catch (Exception e) {
                LOG.error("Error in saving the bankAccount details for customerId : {}", customerId);
                throw new BusinessLogicException(ResponseCode.DUPLICATE_REQUEST_BODY_FIELDS.getCode(), ResponseCode.DUPLICATE_REQUEST_BODY_FIELDS.getMessage());
            }
        } else {
            LOG.info(" Customer doest not exists : {}", customerId);
            throw new BusinessLogicException(ResponseCode.CUSTOMER_DOES_NOT_EXISTS.getCode(), ResponseCode.CUSTOMER_DOES_NOT_EXISTS.getMessage());
        }
    }

    boolean isBankAccountExistsAndActive(Optional<BankAccount> optionalBankAccount) {
        return optionalBankAccount.isPresent() && optionalBankAccount.get().getStatus().equals(BankAccountStatus.ACTIVE);
    }

    @Override
    @Transactional(readOnly = true)
    public Double getAccountBalance(Long customerId, Long accountNumber) {
        if (customerRepository.existsById(customerId)) {
            Optional<BankAccount> optionalBankAccount = bankAccountRepository.findByNumber(accountNumber);
            if (isBankAccountExistsAndActive(optionalBankAccount)) {
                return optionalBankAccount.get().getClosingBalance();
            } else {
                LOG.info("bank account not found or bank account not active : {} ", accountNumber);
                throw new BusinessLogicException(ResponseCode.BANK_ACCOUNT_NOT_FOUND.getCode(), ResponseCode.BANK_ACCOUNT_NOT_FOUND.getMessage());
            }
        } else {
            LOG.info("Customer does not exists : {} ", customerId);
            throw new BusinessLogicException(ResponseCode.CUSTOMER_DOES_NOT_EXISTS.getCode(), ResponseCode.CUSTOMER_DOES_NOT_EXISTS.getMessage());
        }
    }

    boolean isAmountTransferable(Double balance, Double transferAmount) {
        if(Double.compare(transferAmount, 0.0) > 0 && Double.compare(balance, transferAmount) > 0){
            return true;
        }else {
            LOG.info("transfer amount is greater than the amount in account : {}", transferAmount);
            throw new BusinessLogicException(ResponseCode.TRANSFER_AMOUNT_GREATER.getCode(), ResponseCode.TRANSFER_AMOUNT_GREATER.getMessage());
        }
    }

    private BankAccount doTransaction(BankAccount bankAccount, Double withDrawalAmount, Double depositAmount) {
        BankAccount updatedBankAccount = mappingUtils.mapBankAccountEntity(bankAccount, withDrawalAmount, depositAmount);
        BankAccountTransaction bankAccountTransaction =
                mappingUtils.getBankAccountTransactionEntity(bankAccount.getNumber(), bankAccount.getClosingBalance(), withDrawalAmount, depositAmount);
        bankAccountTransactionRepository.save(bankAccountTransaction);
        return bankAccountRepository.save(updatedBankAccount);

    }

    @Override
    @Transactional
    public void transferMoney(Long customerId, Long fromAccount, Long toAccount, Double transferAmount) {
        if (customerRepository.existsById(customerId)) {
            Optional<BankAccount> optionalFromBankAccount = bankAccountRepository.findByNumber(fromAccount);
            Optional<BankAccount> optionalToBankAccount = bankAccountRepository.findByNumber(toAccount);
            if (isBankAccountExistsAndActive(optionalFromBankAccount) && isBankAccountExistsAndActive(optionalToBankAccount)
                    && isAmountTransferable(optionalFromBankAccount.get().getClosingBalance(), transferAmount)) {
                doTransaction(optionalFromBankAccount.get(), transferAmount, 0.0);
                doTransaction(optionalToBankAccount.get(), 0.0, transferAmount);
            } else {
                LOG.info("bank account not found or bank account not active : {}  {} ", fromAccount, toAccount);
                throw new BusinessLogicException(ResponseCode.BANK_ACCOUNT_NOT_FOUND.getCode(), ResponseCode.BANK_ACCOUNT_NOT_FOUND.getMessage());
            }
        } else {
            LOG.info("Customer does not exists : {} ", customerId);
            throw new BusinessLogicException(ResponseCode.CUSTOMER_DOES_NOT_EXISTS.getCode(), ResponseCode.CUSTOMER_DOES_NOT_EXISTS.getMessage());
        }

    }

    @Override
    @Transactional
    public void depositMoney(Long customerId, Long accountNumber, Double depositAmount) {
        if (customerRepository.existsById(customerId)) {
            Optional<BankAccount> optionalBankAccount = bankAccountRepository.findByNumber(accountNumber);
            if (isBankAccountExistsAndActive(optionalBankAccount) && Double.compare(depositAmount, 0.0) > 0) {
                doTransaction(optionalBankAccount.get(), 0.0, depositAmount);
            } else {
                LOG.info("bank account not found or bank account not active : {} ", accountNumber);
                throw new BusinessLogicException(ResponseCode.BANK_ACCOUNT_NOT_FOUND.getCode(), ResponseCode.BANK_ACCOUNT_NOT_FOUND.getMessage());
            }

        } else {
            LOG.info("Customer does not exists : {} ", customerId);
            throw new BusinessLogicException(ResponseCode.CUSTOMER_DOES_NOT_EXISTS.getCode(), ResponseCode.CUSTOMER_DOES_NOT_EXISTS.getMessage());
        }

    }

}

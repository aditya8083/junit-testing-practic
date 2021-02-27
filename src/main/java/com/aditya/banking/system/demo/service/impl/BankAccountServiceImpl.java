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
                BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
                CustomerAccount customerAccount = mappingUtils.getCustomerAccountEntity(savedBankAccount, customerId);
                customerAccountRepository.save(customerAccount);
                return savedBankAccount;
            } catch (Exception e) {
                LOG.error("Error in saving the bankAccount details for customerId : {}", customerId);
                throw new BusinessLogicException(ResponseCode.SYSTEM_ERROR.getCode(), ResponseCode.SYSTEM_ERROR.getMessage());
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
        return Double.compare(transferAmount, 0.0) > 0 && Double.compare(balance, transferAmount) > 0;
    }

    private void doTransfer(BankAccount fromAccount, BankAccount toAccount, Double transferAmount) {
        BankAccount updatedFromAccount = mappingUtils.mapBankAccountEntity(fromAccount, transferAmount, 0.0);
        BankAccount updatedToAccount = mappingUtils.mapBankAccountEntity(toAccount, 0.0, transferAmount);
        BankAccountTransaction fromBankAccountTransaction = mappingUtils.getBankAccountTransactionEntity(fromAccount.getNumber(),fromAccount.getClosingBalance(), transferAmount, 0.0);
        BankAccountTransaction toBankAccountTransaction = mappingUtils.getBankAccountTransactionEntity(toAccount.getNumber(), toAccount.getClosingBalance(), 0.0, transferAmount);
        bankAccountTransactionRepository.save(fromBankAccountTransaction);
        bankAccountTransactionRepository.save(toBankAccountTransaction);
        bankAccountRepository.save(updatedFromAccount);
        bankAccountRepository.save(updatedToAccount);
    }

    @Override
    @Transactional
    public void transferMoney(Long customerId, Long fromAccount, Long toAccount, Double transferAmount) {
        if (customerRepository.existsById(customerId)) {
            Optional<BankAccount> optionalFromBankAccount = bankAccountRepository.findByNumber(fromAccount);
            Optional<BankAccount> optionalToBankAccount = bankAccountRepository.findByNumber(fromAccount);
            if (isBankAccountExistsAndActive(optionalFromBankAccount) && isBankAccountExistsAndActive(optionalToBankAccount)
                    && isAmountTransferable(optionalFromBankAccount.get().getClosingBalance(), transferAmount)) {
                doTransfer(optionalFromBankAccount.get(), optionalToBankAccount.get(), transferAmount);
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
    public void depositMoney(Long customerId, Long accountNumber, Double amount) {

    }

}

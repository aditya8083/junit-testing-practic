package com.aditya.banking.system.demo.utils;

import com.aditya.banking.system.demo.entity.dao.*;
import com.aditya.banking.system.demo.model.request.UserAccountModel;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class MappingUtils {

    public Customer mapUserAccountToCustomerEntity(UserAccountModel userAccount) {
        return new DozerBeanMapper().map(userAccount, Customer.class);
    }

    public CustomerAccount getCustomerAccountEntity(BankAccount bankAccount, Long customerId) {
        CustomerAccount customerAccount = new CustomerAccount();
        customerAccount.setBankId(bankAccount.getId());
        customerAccount.setCreatedBy(bankAccount.getCreatedBy());
        customerAccount.setCreatedDate(bankAccount.getCreatedDate());
        customerAccount.setUpdatedBy(customerId.toString());
        customerAccount.setUpdatedDate(new Date());
        customerAccount.setCustomerId(customerId);
        return customerAccount;
    }

    public BankAccount mapBankAccountEntity(BankAccount previousAccount, double withdrawalAmount, double depositAmount) {
        BankAccount bankAccount = new BankAccount();
        settleBalances(bankAccount, previousAccount, withdrawalAmount, depositAmount);
        bankAccount.setId(previousAccount.getId());
        bankAccount.setInterestRate(previousAccount.getInterestRate());
        bankAccount.setNumber(previousAccount.getNumber());
        bankAccount.setBankBranchId(previousAccount.getBankBranchId());
        bankAccount.setStatus(previousAccount.getStatus());
        bankAccount.setType(previousAccount.getType());
        bankAccount.setUpdatedDate(new Date());
        bankAccount.setUpdatedBy(previousAccount.getUpdatedBy());
        bankAccount.setCreatedBy(previousAccount.getCreatedBy());
        bankAccount.setCreatedDate(previousAccount.getCreatedDate());
        return bankAccount;
    }

    private void settleBalances(BankAccount bankAccount, BankAccount previousAccount, double withdrawalAmount, double depositAmount) {
        bankAccount.setClosingBalance(roundDoubleValue(previousAccount.getClosingBalance() - withdrawalAmount + depositAmount));
        bankAccount.setDepositAmount(depositAmount);
        bankAccount.setWithdrawalAmount(withdrawalAmount);
    }

    public BankAccountTransaction getBankAccountTransactionEntity(Long bankAccountNumber, Double previousClosingBalance, double withdrawalAmount, double depositAmount) {
        BankAccountTransaction bankAccountTransaction= new BankAccountTransaction();
        bankAccountTransaction.setBankAccountNumber(bankAccountNumber);
        bankAccountTransaction.setClosingBalance(roundDoubleValue(previousClosingBalance - withdrawalAmount + depositAmount));
        bankAccountTransaction.setDepositAmount(depositAmount);
        bankAccountTransaction.setWithdrawalAmount(withdrawalAmount);
        bankAccountTransaction.setTransactionDate(new Date());
        return bankAccountTransaction;
    }

    public Double roundDoubleValue(Double value){
        return  Math.round(value * 100.0) / 100.0;
    }
}

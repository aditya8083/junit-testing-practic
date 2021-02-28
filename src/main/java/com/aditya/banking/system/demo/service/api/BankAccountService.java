package com.aditya.banking.system.demo.service.api;

import com.aditya.banking.system.demo.entity.dao.BankAccount;
import com.aditya.banking.system.demo.entity.dao.BankAccountTransaction;

import java.util.List;

public interface BankAccountService {
    /**
     * Create Bank Account for given customerId by Admin or User
     *
     * @param customerId  customerId
     * @param bankAccount bankAccount details
     * @return creates bankAccount
     */
    BankAccount createBankAccount(Long customerId, BankAccount bankAccount);

    /**
     * Get account balance from Bank Account using customerId by Admin or User
     *
     * @param customerId    customerId
     * @param accountNumber accountNumber
     * @return account balance
     */
    Double getAccountBalance(Long customerId, Long accountNumber);

    /**
     * Transfer money from Bank Account by Admin or User
     *
     * @param customerId     customerId
     * @param fromAccount    bankAccount from which amount to be transferred
     * @param toAccount      toAccount where amount to be transferred
     * @param transferAmount amount to be transferred
     * @return void
     */
    void transferMoney(Long customerId, Long fromAccount, Long toAccount, Double transferAmount);

    /**
     * Deposit money in Bank Account for given account number by Admin or User
     *
     * @param customerId    customerId
     * @param accountNumber accountNumber where amount to be deposited
     * @param amount        amount to be deposited
     * @return void
     */
    void depositMoney(Long customerId, Long accountNumber, Double amount);

    /**
     * Calculate interest for given Bank Account number by Admin or User
     *
     * @param customerId    customerId
     * @param accountNumber accountNumber
     * @param yearsPassed   Number of years for which interest to be calculated
     * @return interest earned for that period
     */
    Double calculateInterest(Long customerId, Long accountNumber, Long yearsPassed);

    /**
     * Print account statement for given Bank Account by Admin or User
     *
     * @param customerId    customerId
     * @param accountNumber accountNumber
     * @return pdf of transactions for given accountNumber
     */
    List<BankAccountTransaction> printAccountStatement(Long customerId, Long accountNumber);
}

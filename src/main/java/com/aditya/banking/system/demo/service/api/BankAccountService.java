package com.aditya.banking.system.demo.service.api;

import com.aditya.banking.system.demo.entity.dao.BankAccount;

public interface BankAccountService {
    BankAccount createBankAccount(Long customerId, BankAccount bankAccount);

    Double getAccountBalance(Long customerId, Long accountNumber);

    void transferMoney(Long customerId, Long fromAccount, Long toAccount, Double transferAmount);

    void depositMoney(Long customerId, Long accountNumber, Double amount);
}

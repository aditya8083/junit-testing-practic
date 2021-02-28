package com.aditya.banking.system.demo.service.api;

import com.aditya.banking.system.demo.entity.dao.Bank;

public interface BankService {

    Bank saveBank(String userId, Bank bank);

    Bank getBankDetails(String userId, Long bankId);

    Bank updateBank(String userId, Bank bank, Long bankId);

    void deleteBank(String userId, Long bankId);
}

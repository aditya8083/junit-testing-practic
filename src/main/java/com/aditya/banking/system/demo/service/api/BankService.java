package com.aditya.banking.system.demo.service.api;

import com.aditya.banking.system.demo.entity.dao.Bank;

public interface BankService {
    Bank saveBank(Long userId, Bank bank);

    Bank getBankDetails(Long userId, Long bankId);

    Bank updateBank(Long userId, Bank bank, Long bankId);

    void deleteBank(Long userId, Long bankId);
}

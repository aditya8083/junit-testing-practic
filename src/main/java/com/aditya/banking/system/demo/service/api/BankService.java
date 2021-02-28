package com.aditya.banking.system.demo.service.api;

import com.aditya.banking.system.demo.entity.dao.Bank;

public interface BankService {

    /**
     * Create Bank details by Admin only
     *
     * @param userId admin userId
     * @param bank   bankDetails
     * @return saved bankDetails
     */
    Bank saveBank(String userId, Bank bank);

    /**
     * Get Bank details by Admin only
     *
     * @param userId admin userId
     * @param bankId bankId
     * @return bankDetails
     */
    Bank getBankDetails(String userId, Long bankId);

    /**
     * Update Bank details by Admin only
     *
     * @param userId admin userId
     * @param bank   bankDetails
     * @param bankId bankId
     * @return bankDetails
     */
    Bank updateBank(String userId, Bank bank, Long bankId);

    /**
     * Delete Bank details by Admin only
     *
     * @param userId admin userId
     * @param bankId bankId
     * @return delete bank
     */
    void deleteBank(String userId, Long bankId);
}

package com.aditya.banking.system.demo.service.impl;

import com.aditya.banking.system.demo.dao.BankRepository;
import com.aditya.banking.system.demo.dao.EmployeeRepository;
import com.aditya.banking.system.demo.entity.dao.Bank;
import com.aditya.banking.system.demo.service.api.BankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BankServiceImpl implements BankService {
    private static final Logger LOG = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    BankRepository bankRepository;

    @Autowired
    AdminServiceImpl adminService;

    @Override
    public Bank saveBank(Long userId, Bank bank) {
        if(adminService.isAdminUser(userId)){
            bank.setCreatedBy(userId);
            bank.setCreatedDate(new Date());
            bank.setUpdatedBy(userId);
            bank.setUpdatedDate(new Date());
            bankRepository.save(bank);
        }
        return null;
    }

    @Override
    public Bank getBankDetails(Long userId, Long bankId) {
        if(adminService.isAdminUser(userId)){
            return bankRepository.findById(bankId).get();
        }
        return null;
    }

    @Override
    public Bank updateBank(Long userId, Bank bank, Long bankId) {
        if(adminService.isAdminUser(userId)){
            Bank savedBank = bankRepository.findById(bankId).get();
            bank.setId(bankId);
            bank.setUpdatedBy(userId);
            bank.setUpdatedDate(new Date());
            bank.setCreatedBy(savedBank.getCreatedBy());
            bank.setCreatedDate(savedBank.getCreatedDate());
            return bankRepository.save(bank);
        }
        return null;
    }

    @Override
    public void deleteBank(Long userId, Long bankId) {
        if(adminService.isAdminUser(userId)) {
            bankRepository.deleteById(bankId);
        }
    }
}

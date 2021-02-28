package com.aditya.banking.system.demo.service.impl;

import com.aditya.banking.system.demo.dao.BankRepository;
import com.aditya.banking.system.demo.entity.constant.enums.ResponseCode;
import com.aditya.banking.system.demo.entity.dao.Bank;
import com.aditya.banking.system.demo.exception.BusinessLogicException;
import com.aditya.banking.system.demo.service.api.BankService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BankServiceImpl implements BankService {

    private static final Logger LOG = LoggerFactory.getLogger(BankServiceImpl.class);

    @Autowired
    BankRepository bankRepository;


    @Override
    public Bank saveBank(String userId, Bank bank) {
        try {
            bank.setCreatedBy(userId);
            bank.setCreatedDate(new Date());
            bank.setUpdatedBy(userId);
            bank.setUpdatedDate(new Date());
            return bankRepository.save(bank);
        } catch (Exception e) {
            LOG.error("Error in saving the Bank details ");
            throw new BusinessLogicException(ResponseCode.DUPLICATE_REQUEST_BODY_FIELDS.getCode(), ResponseCode.DUPLICATE_REQUEST_BODY_FIELDS.getMessage());
        }

    }

    @Override
    public Bank getBankDetails(String userId, Long bankId) {
        if (bankRepository.existsById(bankId)) {
            return bankRepository.findById(bankId).get();
        } else {
            LOG.info("Bank does not exists : {} ", bankId);
            throw new BusinessLogicException(ResponseCode.BANK_DOES_NOT_EXISTS.getCode(), ResponseCode.BANK_DOES_NOT_EXISTS.getMessage());
        }

    }

    @Override
    public Bank updateBank(String userId, Bank bank, Long bankId) {
        if (bankRepository.existsById(bankId)) {
            Bank savedBank = bankRepository.findById(bankId).get();
            bank.setId(bankId);
            bank.setUpdatedBy(userId);
            bank.setUpdatedDate(new Date());
            bank.setCreatedBy(savedBank.getCreatedBy());
            bank.setCreatedDate(savedBank.getCreatedDate());
            return bankRepository.save(bank);
        } else {
            LOG.info("Bank does not exists : {} ", bankId);
            throw new BusinessLogicException(ResponseCode.BANK_DOES_NOT_EXISTS.getCode(), ResponseCode.BANK_DOES_NOT_EXISTS.getMessage());
        }
    }

    @Override
    public void deleteBank(String userId, Long bankId) {
        if (bankRepository.existsById(bankId)) {
            bankRepository.deleteById(bankId);
        } else {
            LOG.info("Bank does not exists : {} ", bankId);
            throw new BusinessLogicException(ResponseCode.BANK_DOES_NOT_EXISTS.getCode(), ResponseCode.BANK_DOES_NOT_EXISTS.getMessage());
        }
    }

}

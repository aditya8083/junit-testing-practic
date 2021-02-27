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

    @Autowired
    AdminServiceImpl adminService;

    @Override
    public Bank saveBank(Long userId, Bank bank) {
        if(adminService.isAdminUser(userId)){
            try {
            bank.setCreatedBy(userId);
            bank.setCreatedDate(new Date());
            bank.setUpdatedBy(userId);
            bank.setUpdatedDate(new Date());
            return bankRepository.save(bank);
            } catch (Exception e) {
                LOG.error("Error in saving the Bank details ");
                throw new BusinessLogicException(ResponseCode.SYSTEM_ERROR.getCode(), ResponseCode.SYSTEM_ERROR.getMessage());
            }
        } else {
            LOG.info("You are not an admin : {}", userId);
            throw new BusinessLogicException(ResponseCode.NOT_AUTHORIZED_ERROR.getCode(), ResponseCode.NOT_AUTHORIZED_ERROR.getMessage());
        }
    }

    @Override
    public Bank getBankDetails(Long userId, Long bankId) {
        if(adminService.isAdminUser(userId)){
            if(bankRepository.existsById(bankId)) {
                return bankRepository.findById(bankId).get();
            } else {
                LOG.info("Bank does not exists : {} ", bankId);
                throw new BusinessLogicException(ResponseCode.BANK_DOES_NOT_EXISTS.getCode(), ResponseCode.BANK_DOES_NOT_EXISTS.getMessage());
            }
        } else {
            LOG.info("You are not an admin : {}", userId);
            throw new BusinessLogicException(ResponseCode.NOT_AUTHORIZED_ERROR.getCode(), ResponseCode.NOT_AUTHORIZED_ERROR.getMessage());
        }
    }

    @Override
    public Bank updateBank(Long userId, Bank bank, Long bankId) {
        if(adminService.isAdminUser(userId)){
            if(bankRepository.existsById(bankId)) {
                Bank savedBank = bankRepository.findById(bankId).get();
                bank.setId(bankId);
                bank.setUpdatedBy(userId);
                bank.setUpdatedDate(new Date());
                bank.setCreatedBy(savedBank.getCreatedBy());
                bank.setCreatedDate(savedBank.getCreatedDate());
                return bankRepository.save(bank);
            }else {
                LOG.info("Bank does not exists : {} ", bankId);
                throw new BusinessLogicException(ResponseCode.BANK_DOES_NOT_EXISTS.getCode(), ResponseCode.BANK_DOES_NOT_EXISTS.getMessage());
            }
        } else {
            LOG.info("You are not admin : {} ", userId);
            throw new BusinessLogicException(ResponseCode.NOT_AUTHORIZED_ERROR.getCode(), ResponseCode.NOT_AUTHORIZED_ERROR.getMessage());
        }
    }

    @Override
    public void deleteBank(Long userId, Long bankId) {
        if(adminService.isAdminUser(userId)) {
            if(bankRepository.existsById(bankId)) {
                bankRepository.deleteById(bankId);
            } else {
                LOG.info("Bank does not exists : {} ", bankId);
                throw new BusinessLogicException(ResponseCode.BANK_DOES_NOT_EXISTS.getCode(), ResponseCode.BANK_DOES_NOT_EXISTS.getMessage());
            }
        } else {
            LOG.info("You are not an admin : {}", userId);
            throw new BusinessLogicException(ResponseCode.NOT_AUTHORIZED_ERROR.getCode(), ResponseCode.NOT_AUTHORIZED_ERROR.getMessage());
        }
    }

}

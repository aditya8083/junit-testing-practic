package com.aditya.banking.system.demo.controller;

import com.aditya.banking.system.demo.dao.BankRepository;
import com.aditya.banking.system.demo.entity.constant.ApiPath;
import com.aditya.banking.system.demo.entity.dao.Bank;
import com.aditya.banking.system.demo.model.request.BankModel;
import com.aditya.banking.system.demo.service.api.BankService;
import com.aditya.banking.system.demo.utils.RequestMappingUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin("*")
@RequestMapping(ApiPath.BANK)
public class BankController {

    private static final Logger LOG = LoggerFactory.getLogger(BankController.class);

    @Autowired
    BankRepository bankRepository;

    @Autowired
    BankService bankService;

    @Autowired
    RequestMappingUtils requestMappingUtils;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Bank> addBank(@RequestParam(value = "userId") Long userId,
                                        @RequestBody BankModel bankModel) throws IOException {

        Bank bank = requestMappingUtils.mapBankModelRequest(bankModel);
        Bank savedBank = bankService.saveBank(userId, bank);
        return new ResponseEntity<>(savedBank, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Bank> getBankDetails(@RequestParam(value = "userId") Long userId,
                                               @RequestParam(value = "bankId") Long bankId) throws IOException {
        Bank savedBankDetails = bankService.getBankDetails(userId, bankId);
        return new ResponseEntity<>(savedBankDetails, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Bank> updateBank(@RequestParam(value = "userId") Long userId,
                                           @RequestBody BankModel bankModel, @RequestParam(value = "bankId") Long bankId) throws IOException {
        Bank bank = requestMappingUtils.mapBankModelRequest(bankModel);
        Bank updatedBank = bankService.updateBank(userId, bank, bankId);
        return new ResponseEntity<>(updatedBank, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteBank(@RequestParam(value = "userId") Long userId,
                                             @RequestParam(value = "bankId") Long bankId) throws IOException {
        bankService.deleteBank(userId, bankId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}

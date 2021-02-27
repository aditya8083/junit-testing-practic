package com.aditya.banking.system.demo.controller;

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

@RestController
@CrossOrigin("*")
@RequestMapping(ApiPath.BANK)
public class BankController {

    private static final Logger LOG = LoggerFactory.getLogger(BankController.class);

    @Autowired
    BankService bankService;

    @Autowired
    RequestMappingUtils requestMappingUtils;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> addBank(@RequestParam(value = "userId") Long userId,
                                          @RequestBody BankModel bankModel) {
        try {
            Bank bank = requestMappingUtils.mapBankModelRequest(bankModel);
            Bank savedBank = bankService.saveBank(userId, bank);
            return new ResponseEntity<>(savedBank, HttpStatus.CREATED);
        } catch (Exception exception) {
            LOG.error("Error in saving the bank details data by User : {}, {}", userId, exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> getBankDetails(@RequestParam(value = "userId") Long userId,
                                                 @RequestParam(value = "bankId") Long bankId) {
        try {
            Bank savedBankDetails = bankService.getBankDetails(userId, bankId);
            return new ResponseEntity<>(savedBankDetails, HttpStatus.OK);
        } catch (Exception exception) {
            LOG.error("Error in fetching the bank details by User : {}, {}", userId, exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Object> updateBank(@RequestParam(value = "userId") Long userId,
                                             @RequestBody BankModel bankModel, @RequestParam(value = "bankId") Long bankId) {
        try {
            Bank bank = requestMappingUtils.mapBankModelRequest(bankModel);
            Bank updatedBank = bankService.updateBank(userId, bank, bankId);
            return new ResponseEntity<>(updatedBank, HttpStatus.OK);
        } catch (Exception exception) {
            LOG.error("Error in updating the bank  details by User : {}, {}", userId, exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteBank(@RequestParam(value = "userId") Long userId,
                                             @RequestParam(value = "bankId") Long bankId) {
        try {
            bankService.deleteBank(userId, bankId);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception exception) {
            LOG.error("Error in deleting the bank  details by User : {}, {}", userId, exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }


}

package com.aditya.banking.system.demo.controller;

import com.aditya.banking.system.demo.entity.constant.ApiPath;
import com.aditya.banking.system.demo.entity.constant.enums.ResponseCode;
import com.aditya.banking.system.demo.entity.dao.BankAccount;
import com.aditya.banking.system.demo.model.request.BankAccountModel;
import com.aditya.banking.system.demo.service.api.BankAccountService;
import com.aditya.banking.system.demo.utils.RequestMappingUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping(ApiPath.BANK_ACCOUNT)
public class BankAccountController {

    private static final Logger LOG = LoggerFactory.getLogger(BankAccountController.class);

    @Autowired
    RequestMappingUtils requestMappingUtils;

    @Autowired
    BankAccountService bankAccountService;

    @RequestMapping(value = "/createBankAccount", method = RequestMethod.POST)
    public ResponseEntity<Object> createAccount(@RequestParam(value = "customerId") Long customerId,
                                                @RequestBody BankAccountModel bankAccountModel) {
        try {
            BankAccount bankAccount = requestMappingUtils.mapBankAccountModelRequest(bankAccountModel);
            BankAccount savedBankAccount = bankAccountService.createBankAccount(customerId, bankAccount);
            return new ResponseEntity<>(savedBankAccount, HttpStatus.CREATED);
        } catch (Exception exception) {
            LOG.error("Error in creating the bank account for customer : {}, {}", customerId, exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/depositMoney", method = RequestMethod.PUT)
    public ResponseEntity<Object> depositMoney(@RequestParam(value = "customerId") Long customerId,
                                               @RequestParam Long accountNumber, @RequestParam Double amount) {
        try {
            bankAccountService.depositMoney(customerId, accountNumber, amount);
            return new ResponseEntity<>(ResponseCode.SUCCESS.getMessage(), HttpStatus.OK);
        } catch (Exception exception) {
            LOG.error("Error in fetching the balance for customer from accountNumber : {}, {}", customerId, accountNumber);
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/getBalance", method = RequestMethod.GET)
    public ResponseEntity<Object> getAccountBalance(@RequestParam(value = "customerId") Long customerId,
                                                    @RequestParam Long accountNumber) {
        try {
            Double balance = bankAccountService.getAccountBalance(customerId, accountNumber);
            return new ResponseEntity<>(balance, HttpStatus.OK);
        } catch (Exception exception) {
            LOG.error("Error in fetching the balance for customer from accountNumber : {}, {}", customerId, accountNumber);
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/transferMoney", method = RequestMethod.PUT)
    public ResponseEntity<Object> transferMoney(@RequestParam(value = "customerId") Long customerId,
                                                @RequestParam Long fromAccount, @RequestParam Long toAccount, @RequestParam Double transferAmount) {
        try {
            bankAccountService.transferMoney(customerId, fromAccount, toAccount, transferAmount);
            return new ResponseEntity<>(ResponseCode.SUCCESS.getMessage(), HttpStatus.OK);
        } catch (Exception exception) {
            LOG.error("Error in transferring amount for customer from Account1 to Account2  : {}, {}", customerId, fromAccount, toAccount);
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/printAccountStatement", method = RequestMethod.PUT)
    public ResponseEntity<Object> printAccountStatement(@RequestParam(value = "customerId") Long customerId,
                                                        @RequestParam Long accountNumber) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/calculateInterest", method = RequestMethod.PUT)
    public ResponseEntity<Object> calculateInterest(@RequestParam(value = "customerId") Long customerId,
                                                    @RequestParam Long accountNumber, @RequestParam Long yearsPassed) {
        try {
            Double interestAmount = bankAccountService.calculateInterest(customerId, accountNumber, yearsPassed);
            return new ResponseEntity<>(interestAmount, HttpStatus.OK);
        } catch (Exception exception) {
            LOG.error("Error in calculating interest for account : {}", accountNumber);
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

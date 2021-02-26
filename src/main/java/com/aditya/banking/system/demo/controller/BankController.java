package com.aditya.banking.system.demo.controller;

import com.aditya.banking.system.demo.entity.constant.ApiPath;
import com.aditya.banking.system.demo.model.request.BankModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin("*")
@RequestMapping(ApiPath.BANK)
public class BankController {

    private static final Logger LOG = LoggerFactory.getLogger(BankController.class);

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> addBank(@RequestHeader(value = "userId") String userId,
                                          @RequestBody BankModel bankModel) throws IOException {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> getBankDetails(@RequestHeader(value = "userId") String userId,
                                                 @RequestParam String bankId) throws IOException {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Object> updateBank(@RequestHeader(value = "userId") String userId,
                                             @RequestBody BankModel bankModel) throws IOException {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteBank(@RequestHeader(value = "userId") String userId,
                                             @RequestParam String bankId) throws IOException {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}

package com.aditya.banking.system.demo.controller;


import com.aditya.banking.system.demo.entity.constant.ApiPath;
import com.aditya.banking.system.demo.entity.dao.UserAccount;
import com.aditya.banking.system.demo.model.request.UserAccountModel;
import com.aditya.banking.system.demo.service.api.UserAccountService;
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
@RequestMapping(ApiPath.ACCOUNT)
public class UserAccountController {

    private static final Logger LOG = LoggerFactory.getLogger(UserAccountController.class);

    @Autowired
    RequestMappingUtils requestMappingUtils;

    @Autowired
    UserAccountService commonService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Object> register(@RequestParam(defaultValue = "1234567") Long clientId, @RequestBody UserAccountModel accountModel) {
        UserAccount account = requestMappingUtils.mapUserAccountModelRequest(accountModel);
        try {
            commonService.register(clientId, account);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception exception) {
            LOG.error("Error in registering the user : {}, {}", account.getEmail(), exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.PRECONDITION_FAILED);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.PUT)
    public ResponseEntity<Object> login(@RequestParam Long userId,
                                        @RequestParam String password) throws IOException {
        try {
            commonService.login(userId, password);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }catch (Exception exception) {
            LOG.error("Error in login for user : {}, {}", userId, exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.PRECONDITION_FAILED);
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.PUT)
    public ResponseEntity<Object> logout(@RequestParam Long userId) throws IOException {
        try {
            commonService.logout(userId);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }catch (Exception exception) {
            LOG.error("Error in logout for user : {}, {}", userId, exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.PRECONDITION_FAILED);
        }
    }


}

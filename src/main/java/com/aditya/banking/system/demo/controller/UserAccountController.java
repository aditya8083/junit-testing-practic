package com.aditya.banking.system.demo.controller;


import com.aditya.banking.system.demo.dao.UserAccountRepository;
import com.aditya.banking.system.demo.entity.constant.ApiPath;
import com.aditya.banking.system.demo.exception.BusinessLogicException;
import com.aditya.banking.system.demo.model.request.LoginRequest;
import com.aditya.banking.system.demo.model.request.UserAccountModel;
import com.aditya.banking.system.demo.service.api.UserAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(ApiPath.ACCOUNT)
public class UserAccountController {

    private static final Logger LOG = LoggerFactory.getLogger(UserAccountController.class);


    @Autowired
    UserAccountService userAccountService;

    @Autowired
    UserAccountRepository userAccountRepository;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Object> register(@RequestBody UserAccountModel userAccountModel) {
        try {
            if (userAccountRepository.existsByUsername(userAccountModel.getUserName())) {
                return ResponseEntity.badRequest().body(new BusinessLogicException("412", "Error: Username is already taken!"));
            }
            if (userAccountRepository.existsByEmail(userAccountModel.getEmail())) {
                return ResponseEntity.badRequest().body(new BusinessLogicException("412", "Error: Email is already in use!"));
            }
            userAccountService.register(userAccountModel);
            return new ResponseEntity<>("201", HttpStatus.CREATED);
        } catch (Exception exception) {
            LOG.error("Error in registering the user : {}, {}", userAccountModel.getEmail(), exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.PRECONDITION_FAILED);
        }
    }


    @RequestMapping(value = "/login", method = RequestMethod.PUT)
    public ResponseEntity<Object> login( @RequestBody LoginRequest loginRequest) throws IOException {
        try {
            userAccountService.login(loginRequest);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception exception) {
            LOG.error("Error in login for user : {}, {}", loginRequest.getUsername(), exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.PRECONDITION_FAILED);
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.PUT)
    public ResponseEntity<Object> logout(@RequestParam Long userId) throws IOException {
        try {
            userAccountService.logout(userId);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception exception) {
            LOG.error("Error in logout for user : {}, {}", userId, exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.PRECONDITION_FAILED);
        }
    }


}

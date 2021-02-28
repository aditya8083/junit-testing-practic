package com.aditya.banking.system.demo.controller;

import com.aditya.banking.system.demo.dao.UserAccountRepository;
import com.aditya.banking.system.demo.entity.constant.ApiPath;
import com.aditya.banking.system.demo.exception.BusinessLogicException;
import com.aditya.banking.system.demo.model.request.LoginRequest;
import com.aditya.banking.system.demo.model.request.UserAccountModel;
import com.aditya.banking.system.demo.model.response.LoginResponse;
import com.aditya.banking.system.demo.service.api.UserAccountService;
import com.aditya.banking.system.demo.utils.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(ApiPath.ACCOUNT)
public class UserAccountController {

    private static final Logger LOG = LoggerFactory.getLogger(UserAccountController.class);


    @Autowired
    UserAccountService userAccountService;

    @Autowired
    UserAccountRepository userAccountRepository;


    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody UserAccountModel userAccountModel) {
        try {
            if (userAccountRepository.existsByUsername(userAccountModel.getUsername())) {
                return ResponseEntity.badRequest().body(new MessageResponse("412", "Error: Username is already taken!"));
            }
            if (userAccountRepository.existsByEmail(userAccountModel.getEmail())) {
                return ResponseEntity.badRequest().body(new MessageResponse("412", "Error: Email is already in use!"));
            }
            userAccountService.register(userAccountModel);
            return ResponseEntity.ok(new MessageResponse("201", "User registered successfully!"));
        }catch(Exception ex) {
            LOG.error("Error in registering User :  {}", userAccountModel.getUsername());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        LOG.info("sign-in for user : {}", loginRequest.getUsername());
        LoginResponse loginResponse = userAccountService.login(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }

}

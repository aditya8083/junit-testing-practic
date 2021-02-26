package com.aditya.banking.system.demo.controller;

import com.aditya.banking.system.demo.entity.constant.ApiPath;
import com.aditya.banking.system.demo.model.request.CustomerModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin("*")
@RequestMapping(ApiPath.CUSTOMER)
public class CustomerController {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> addCustomer(@RequestHeader(value = "userId") String userId,
                                              @RequestBody CustomerModel customerModel) throws IOException {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> getCustomerDetails(@RequestHeader(value = "userId") String userId,
                                                     @RequestParam String customerId) throws IOException {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Object> updateCustomer(@RequestHeader(value = "userId") String userId,
                                                 @RequestBody CustomerModel customerModel) throws IOException {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteCustomer(@RequestHeader(value = "userId") String userId,
                                                 @RequestParam String customerId) throws IOException {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}

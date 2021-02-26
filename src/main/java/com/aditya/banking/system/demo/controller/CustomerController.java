package com.aditya.banking.system.demo.controller;

import com.aditya.banking.system.demo.dao.CustomerRepository;
import com.aditya.banking.system.demo.entity.constant.ApiPath;
import com.aditya.banking.system.demo.entity.dao.Customer;
import com.aditya.banking.system.demo.model.request.CustomerModel;
import com.aditya.banking.system.demo.service.api.CustomerService;
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
@RequestMapping(ApiPath.CUSTOMER)
public class CustomerController {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    RequestMappingUtils requestMappingUtils;


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Customer> addCustomer(@RequestParam(value = "userId", defaultValue = "12345678") Long userId ,
                                                @RequestBody CustomerModel customerModel) throws IOException {
        Customer customer = requestMappingUtils.mapCustomerModelRequest(customerModel);
        Customer savedCustomer = customerService.saveCustomer(userId, customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Customer> getCustomerDetails(@RequestParam(value = "userId", defaultValue = "12345678") Long userId,
                                                     @RequestParam(value = "customerId") Long customerId) throws IOException {
        Customer savedCustomer = customerService.getCustomerDetails(userId, customerId);
        return new ResponseEntity<>(savedCustomer, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Customer> updateCustomer(@RequestParam(value = "userId", defaultValue = "12345678") Long userId,
                                                 @RequestBody CustomerModel customerModel, @RequestParam(value = "customerId") Long customerId ) throws IOException {
        Customer customer = requestMappingUtils.mapCustomerModelRequest(customerModel);
        Customer updatedCustomerDetails = customerService.updateCustomer(userId, customer,customerId);
        return new ResponseEntity<>(updatedCustomerDetails, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteCustomer(@RequestParam(value = "userId", defaultValue = "12345678") Long userId,
                                                 @RequestParam(value = "customerId") Long customerId) throws IOException {
        customerService.deleteCustomer(userId, customerId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}

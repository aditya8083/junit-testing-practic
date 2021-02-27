package com.aditya.banking.system.demo.controller;

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

@RestController
@CrossOrigin("*")
@RequestMapping(ApiPath.CUSTOMER)
public class CustomerController {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerService customerService;

    @Autowired
    RequestMappingUtils requestMappingUtils;


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> addCustomer(@RequestParam(value = "userId", defaultValue = "12345678") Long userId,
                                              @RequestBody CustomerModel customerModel) {
        try {
            Customer customer = requestMappingUtils.mapCustomerModelRequest(customerModel);
            Customer savedCustomer = customerService.saveCustomer(userId, customer);
            return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
        } catch (Exception exception) {
            LOG.error("Error in saving the customer data by User : {}, {}", userId, exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> getCustomerDetails(@RequestParam(value = "userId", defaultValue = "12345678") Long userId,
                                                     @RequestParam(value = "customerId") Long customerId) {
        try {
            Customer savedCustomer = customerService.getCustomerDetails(userId, customerId);
            return new ResponseEntity<>(savedCustomer, HttpStatus.OK);
        } catch (Exception exception) {
            LOG.error("Error in getting the customer details : {}, {}", customerId, exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Object> updateCustomer(@RequestParam(value = "userId", defaultValue = "12345678") Long userId,
                                                 @RequestBody CustomerModel customerModel, @RequestParam(value = "customerId") Long customerId) {
        try {
            Customer customer = requestMappingUtils.mapCustomerModelRequest(customerModel);
            Customer updatedCustomerDetails = customerService.updateCustomer(userId, customer, customerId);
            return new ResponseEntity<>(updatedCustomerDetails, HttpStatus.OK);
        } catch (Exception exception) {
            LOG.error("Error in updating the customer details : {}, {}", customerId, exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteCustomer(@RequestParam(value = "userId", defaultValue = "12345678") Long userId,
                                                 @RequestParam(value = "customerId") Long customerId) {
        try {
            customerService.deleteCustomer(userId, customerId);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception exception) {
            LOG.error("Error in deleting the customer details : {}, {}", customerId, exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

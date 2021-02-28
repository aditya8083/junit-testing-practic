package com.aditya.banking.system.demo.controller;

import com.aditya.banking.system.demo.entity.constant.ApiPath;
import com.aditya.banking.system.demo.entity.constant.enums.ResponseCode;
import com.aditya.banking.system.demo.entity.dao.Customer;
import com.aditya.banking.system.demo.model.request.CustomerModel;
import com.aditya.banking.system.demo.service.api.CustomerService;
import com.aditya.banking.system.demo.utils.RequestMappingUtils;

import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @RequestMapping(method = RequestMethod.PUT)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @ApiOperation(value = "Update customer by Admin and User Both")
    public ResponseEntity<Object> updateCustomer(
            @RequestBody CustomerModel customerModel, @RequestHeader(value = "customerId") Long customerId) {
        try {
            Customer customer = requestMappingUtils.mapCustomerModelRequest(customerModel);
            Customer updatedCustomerDetails = customerService.updateCustomer( customer, customerId);
            return new ResponseEntity<>(updatedCustomerDetails, HttpStatus.OK);
        } catch (Exception exception) {
            LOG.error("Error in updating the customer details : {}, {}", customerId, exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/updateKyc", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @ApiOperation(value = "Update kyc info by Admin and User Both")
    public ResponseEntity<Object> updateKycCustomer(
            @RequestHeader String kycId, @RequestHeader(value = "customerId") Long customerId) {
        try {
            Customer updatedCustomerDetails = customerService.updateKycDetails( kycId, customerId);
            return new ResponseEntity<>(updatedCustomerDetails, HttpStatus.OK);
        } catch (Exception exception) {
            LOG.error("Error in updating the customer details : {}, {}", customerId, exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @ApiOperation(value = "Get saved customerDetails by Admin and User Both")
    public ResponseEntity<Object> getCustomerDetails(@RequestHeader(value = "customerId") Long customerId) {
        try {
            Customer savedCustomer = customerService.getCustomerDetails(customerId);
            return new ResponseEntity<>(savedCustomer, HttpStatus.OK);
        } catch (Exception exception) {
            LOG.error("Error in getting the customer details : {}, {}", customerId, exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Delete customer by Admin Only")
    public ResponseEntity<Object> deleteCustomer(@RequestHeader(value = "customerId") Long customerId) {
        try {
            customerService.deleteCustomer(customerId);
            return new ResponseEntity<>(ResponseCode.SUCCESS.getMessage(), HttpStatus.OK);
        } catch (Exception exception) {
            LOG.error("Error in deleting the customer details : {}, {}", customerId, exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

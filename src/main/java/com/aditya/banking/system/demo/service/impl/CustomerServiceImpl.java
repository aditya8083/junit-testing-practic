package com.aditya.banking.system.demo.service.impl;

import com.aditya.banking.system.demo.exception.BusinessLogicException;
import com.aditya.banking.system.demo.dao.CustomerRepository;
import com.aditya.banking.system.demo.entity.constant.enums.ResponseCode;
import com.aditya.banking.system.demo.entity.dao.Customer;
import com.aditya.banking.system.demo.service.api.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer saveCustomer(Long userId, Customer customer) {
        try {
            customer.setCreatedBy(userId);
            customer.setCreatedDate(new Date());
            customer.setUpdatedBy(userId);
            customer.setUpdatedDate(new Date());
            return customerRepository.save(customer);
        } catch (Exception e) {
            LOG.error("Error in saving the customer details ");
            throw new BusinessLogicException(ResponseCode.SYSTEM_ERROR.getCode(), ResponseCode.SYSTEM_ERROR.getMessage());
        }
    }

    @Override
    public Customer getCustomerDetails(Long userId, Long customerId) {
        if (customerRepository.existsById(customerId)) {
            return customerRepository.findById(customerId).get();
        } else {
            LOG.info(" Customer doest not exists : {}", customerId);
            throw new BusinessLogicException(ResponseCode.CUSTOMER_DOES_NOT_EXISTS.getCode(), ResponseCode.CUSTOMER_DOES_NOT_EXISTS.getMessage());
        }
    }

    @Override
    public Customer updateCustomer(Long userId, Customer customer, Long customerId) {
        if (customerRepository.existsById(customerId)) {
            Customer savedCustomer = customerRepository.findById(customerId).get();
            customer.setId(customerId);
            customer.setCreatedBy(savedCustomer.getCreatedBy());
            customer.setCreatedDate(savedCustomer.getCreatedDate());
            customer.setUpdatedBy(userId);
            customer.setUpdatedDate(new Date());
            return customerRepository.save(customer);
        } else {
            LOG.info("Customer does not exists : {} ", customerId);
            throw new BusinessLogicException(ResponseCode.CUSTOMER_DOES_NOT_EXISTS.getCode(), ResponseCode.CUSTOMER_DOES_NOT_EXISTS.getMessage());
        }

    }

    @Override
    public void deleteCustomer(Long userId, Long customerId) {
        if (customerRepository.existsById(customerId)) {
            customerRepository.deleteById(customerId);
        } else {
            LOG.info("Customer does not exists : {} ", customerId);
            throw new BusinessLogicException(ResponseCode.CUSTOMER_DOES_NOT_EXISTS.getCode(), ResponseCode.CUSTOMER_DOES_NOT_EXISTS.getMessage());
        }
    }
}

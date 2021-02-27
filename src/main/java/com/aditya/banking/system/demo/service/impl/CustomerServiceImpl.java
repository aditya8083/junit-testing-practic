package com.aditya.banking.system.demo.service.impl;

import com.aditya.banking.system.demo.dao.CustomerRepository;
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
        customer.setCreatedBy(userId);
        customer.setCreatedDate(new Date());
        customer.setUpdatedBy(userId);
        customer.setUpdatedDate(new Date());
        return customerRepository.save(customer);

    }

    @Override
    public Customer getCustomerDetails(Long userId, Long customerId) {
        return customerRepository.findById(customerId).get();
    }

    @Override
    public Customer updateCustomer(Long userId, Customer customer, Long customerId) {
        Customer savedCustomer = getCustomerDetails(userId, customerId);
        customer.setId(customerId);
        customer.setCreatedBy(savedCustomer.getCreatedBy());
        customer.setCreatedDate(savedCustomer.getCreatedDate());
        customer.setUpdatedBy(userId);
        customer.setUpdatedDate(new Date());
        customerRepository.save(customer);
        return null;
    }

    @Override
    public void deleteCustomer(Long userId, Long customerId) {
        customerRepository.deleteById(customerId);
    }
}

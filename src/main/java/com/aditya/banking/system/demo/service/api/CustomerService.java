package com.aditya.banking.system.demo.service.api;

import com.aditya.banking.system.demo.entity.dao.Customer;

public interface CustomerService {
    Customer saveCustomer(Long userId, Customer customer);

    Customer getCustomerDetails(Long userId, Long customerId);

    Customer updateCustomer(Long userId, Customer customer, Long customerId);

    void deleteCustomer(Long userId, Long customerId);
}

package com.aditya.banking.system.demo.service.api;

import com.aditya.banking.system.demo.entity.dao.Customer;

public interface CustomerService {
    Customer saveCustomer(Customer customer);

    Customer getCustomerDetails(Long customerId);

    Customer updateCustomer(Customer customer, Long customerId);

    void deleteCustomer(Long customerId);
}

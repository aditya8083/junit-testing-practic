package com.aditya.banking.system.demo.service.api;

import com.aditya.banking.system.demo.entity.dao.Customer;

public interface CustomerService {

    /**
     * Save Customer details by Admin and User Both
     *
     * @param customer customerDetails
     * @return saved customerDetails
     */
    Customer saveCustomer(Customer customer);

    /**
     * get Customer details by Admin and User Both
     *
     * @param customerId customerId
     * @return get customerDetails
     */
    Customer getCustomerDetails(Long customerId);

    /**
     * Update Customer details by Admin and User Both
     *
     * @param customer   customerDetails
     * @param customerId customerId
     * @return updated customerDetails
     */
    Customer updateCustomer(Customer customer, Long customerId);

    /**
     * Delete Customer details by Admin and User Both
     *
     * @param customerId customerId
     * @return delete customer
     */
    void deleteCustomer(Long customerId);
}

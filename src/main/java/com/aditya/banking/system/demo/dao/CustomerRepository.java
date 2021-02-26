package com.aditya.banking.system.demo.dao;

import com.aditya.banking.system.demo.entity.dao.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}

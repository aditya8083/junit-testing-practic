package com.aditya.banking.system.demo.dao;

import com.aditya.banking.system.demo.entity.dao.CustomerAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Long> {
}

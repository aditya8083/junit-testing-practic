package com.aditya.banking.system.demo.dao;

import com.aditya.banking.system.demo.entity.dao.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
}

package com.aditya.banking.system.demo.dao;

import com.aditya.banking.system.demo.entity.dao.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    Optional<BankAccount> findByNumber(Long accountNumber);
}

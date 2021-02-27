package com.aditya.banking.system.demo.dao;

import com.aditya.banking.system.demo.entity.dao.BankAccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountTransactionRepository extends JpaRepository<BankAccountTransaction, Long> {

}

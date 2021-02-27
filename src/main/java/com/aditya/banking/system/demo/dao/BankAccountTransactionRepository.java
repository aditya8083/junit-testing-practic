package com.aditya.banking.system.demo.dao;

import com.aditya.banking.system.demo.entity.dao.BankAccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountTransactionRepository extends JpaRepository<BankAccountTransaction, Long> {
    @Query(value = "SELECT * FROM bank_account_transaction bat WHERE bat.bank_account_number = ?1 order by bat.transaction_date desc",nativeQuery=true)
    List<BankAccountTransaction> findByBankAccountNumberSortByDate(Long accountNumber);
}

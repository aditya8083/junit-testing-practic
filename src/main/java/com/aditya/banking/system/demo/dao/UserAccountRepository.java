package com.aditya.banking.system.demo.dao;

import com.aditya.banking.system.demo.entity.dao.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    Optional<UserAccount> findByIdAndPassword(Long userId, String password);

    Optional<UserAccount> findByEmail(String emailId);
}

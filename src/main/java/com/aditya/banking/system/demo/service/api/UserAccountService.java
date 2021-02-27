package com.aditya.banking.system.demo.service.api;

import com.aditya.banking.system.demo.entity.dao.UserAccount;

public interface UserAccountService {

    void register(Long client, UserAccount account);

    void login(Long userId, String password);

    void logout(Long userId);
}

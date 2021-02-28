package com.aditya.banking.system.demo.service.api;

import com.aditya.banking.system.demo.model.request.UserAccountModel;

public interface UserAccountService {

    void register(UserAccountModel account);

   /* UserDetailsImpl login(LoginRequest loginRequest);

    void logout(Long userId);*/
}

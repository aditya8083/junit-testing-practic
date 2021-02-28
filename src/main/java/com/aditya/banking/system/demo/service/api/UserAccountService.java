package com.aditya.banking.system.demo.service.api;

import com.aditya.banking.system.demo.model.request.LoginRequest;
import com.aditya.banking.system.demo.model.request.UserAccountModel;
import com.aditya.banking.system.demo.model.response.LoginResponse;

public interface UserAccountService {

    void register(UserAccountModel account);

    LoginResponse login(LoginRequest loginRequest);

}

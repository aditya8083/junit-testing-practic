package com.aditya.banking.system.demo.model.request;

import lombok.*;

import javax.persistence.Column;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountModel {

    private String email;
    private String contactNo;
    private String password;
    private String firstName;
    private String lastName;
    private boolean isLoggedIn = true;
    private boolean isEmployee = false;

}

package com.aditya.banking.system.demo.model.request;

import lombok.*;

import javax.persistence.Column;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountModel {

    @NonNull
    private Long userName;

    @NonNull
    private String email;

    @NonNull
    private String contactNo;

    @NonNull
    private String password;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    private Set<String> role;

    private boolean isEmployee = false;

    public Long getUserName() {
        return userName;
    }

    public void setUserName(Long userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public boolean isEmployee() {
        return isEmployee;
    }

    public void setEmployee(boolean employee) {
        isEmployee = employee;
    }
}

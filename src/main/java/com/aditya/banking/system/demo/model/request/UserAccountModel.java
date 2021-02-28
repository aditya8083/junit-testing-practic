package com.aditya.banking.system.demo.model.request;

import lombok.*;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountModel {

    @NonNull
    private String username;

    @NonNull
    private String email;

    @NonNull
    private String password;

    private Set<String> role;

  /*  @NonNull
    private String contactNo;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;*/

   // private boolean isEmployee = false;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}

package com.aditya.banking.system.demo.entity.dao;

import com.aditya.banking.system.demo.entity.constant.TableName;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = TableName.USER_ACCOUNT, uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private Long userName;

    @NonNull
    private String email;

    @NonNull
    private String password;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_account_roles",
            joinColumns = @JoinColumn(name = "user_account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public UserAccount(@NonNull Long userName, @NonNull String email, @NonNull String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}

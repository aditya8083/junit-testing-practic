package com.aditya.banking.system.demo.entity.dao;


import com.aditya.banking.system.demo.entity.constant.TableName;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = TableName.USER_ACCOUNT)
@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccount implements Serializable {

    private static final long serialVersionUID = -6251344367342483048L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String contactNo;

    @NonNull
    private String password;

    private String firstName;

    private String lastName;

    private boolean isLoggedIn;

    private boolean isEmployee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public boolean isEmployee() {
        return isEmployee;
    }

    public void setEmployee(boolean employee) {
        isEmployee = employee;
    }
}

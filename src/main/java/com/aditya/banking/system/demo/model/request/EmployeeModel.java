package com.aditya.banking.system.demo.model.request;

import com.aditya.banking.system.demo.entity.constant.enums.EmployeeStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeModel {

    private String firstName;
    private String middleName;
    private String lastName;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String email;
    private String contactNo;
    private boolean isAdmin;
    private Date dateOfBirth;
    private Date joinedDate;
    private Date lastDayOfWork;
    private EmployeeStatus status;

}

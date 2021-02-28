package com.aditya.banking.system.demo.model.request;

import com.aditya.banking.system.demo.entity.constant.enums.EmployeeStatus;
import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeModel {

    private String id;
    @NonNull
    private String firstName;

    private String middleName;
    @NonNull
    private String lastName;
    @NonNull
    private String email;
    @NonNull
    private String contactNo;

    private Date dateOfBirth;
    private Date joinedDate;
    private Date lastDayOfWork;
    private EmployeeStatus status;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String country;

}

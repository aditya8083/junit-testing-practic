package com.aditya.banking.system.demo.model.request;

import com.aditya.banking.system.demo.entity.constant.enums.CustomerStatus;
import com.aditya.banking.system.demo.entity.dao.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerModel {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private Address address;
    private String contactNo;
    private Date dateOfBirth;
    private String kycInfo;
    private CustomerStatus status;

}

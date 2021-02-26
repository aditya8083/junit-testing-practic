package com.aditya.banking.system.demo.model.request;

import com.aditya.banking.system.demo.entity.constant.enums.BankStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankModel {
    private String branchCode;
    private String ifscCode;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String country;
    private BankStatus status;

}

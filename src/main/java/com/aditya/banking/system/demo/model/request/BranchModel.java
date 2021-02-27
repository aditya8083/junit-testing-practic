package com.aditya.banking.system.demo.model.request;

import com.aditya.banking.system.demo.entity.constant.enums.BankBranchStatus;
import com.aditya.banking.system.demo.entity.constant.enums.BankStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BranchModel {
    private String branchCode;
    private String ifscCode;
    private String branchEmail;
    private String branchContactNo;
    private BankBranchStatus status;
    private long bankId;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String country;

}

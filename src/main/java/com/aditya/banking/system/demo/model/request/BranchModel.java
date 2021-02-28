package com.aditya.banking.system.demo.model.request;

import com.aditya.banking.system.demo.entity.constant.enums.BankBranchStatus;
import com.aditya.banking.system.demo.entity.constant.enums.BankStatus;
import lombok.*;

import javax.persistence.Column;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BranchModel {
    private Long id;

    @NonNull
    private String code;

    @NonNull
    private String ifscCode;

    @NonNull
    private String email;

    @NonNull
    private String contactNo;

    @NonNull
    private long bankId;

    private BankBranchStatus status;

    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String country;

}

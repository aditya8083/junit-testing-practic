package com.aditya.banking.system.demo.model.request;

import com.aditya.banking.system.demo.entity.constant.enums.BankStatus;
import com.aditya.banking.system.demo.entity.dao.Address;
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
    private Address address;
    private BankStatus status;

}

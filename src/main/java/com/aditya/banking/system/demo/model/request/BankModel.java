package com.aditya.banking.system.demo.model.request;

import com.aditya.banking.system.demo.entity.constant.enums.BankStatus;
import lombok.*;

import javax.persistence.Column;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankModel {

    private String name;
    private String logo;
    private String email;
    private String contactNo;
    private BankStatus status;

}

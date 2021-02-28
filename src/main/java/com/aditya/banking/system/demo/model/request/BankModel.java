package com.aditya.banking.system.demo.model.request;

import com.aditya.banking.system.demo.entity.constant.enums.BankStatus;
import lombok.*;

import javax.persistence.Column;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankModel {

    @NonNull
    private String name;
    @NonNull
    private String logo;
    @NonNull
    private String email;
    @NonNull
    private String contactNo;
    private BankStatus status;

}

package com.aditya.banking.system.demo.model.request;

import com.aditya.banking.system.demo.entity.constant.enums.AccountStatus;
import com.aditya.banking.system.demo.entity.constant.enums.AccountType;
import com.aditya.banking.system.demo.entity.dao.Bank;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountModel {

    private Long number;
    private Long balance;
    private Double interestRate;
    private AccountType type;
    private AccountStatus status;
    private Bank bankDetails;
}

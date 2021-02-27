package com.aditya.banking.system.demo.model.request;

import com.aditya.banking.system.demo.entity.constant.enums.BankAccountStatus;
import com.aditya.banking.system.demo.entity.constant.enums.BankAccountType;
import com.aditya.banking.system.demo.entity.dao.Bank;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountModel {

    private Long number;
    private Double withdrawalAmount;
    private Double depositAmount;
    private Double closingBalance;
    private Double interestRate;
    private BankAccountType type;
    private BankAccountStatus status;
    private Long bankBranchId;
}

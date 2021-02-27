package com.aditya.banking.system.demo.entity.dao;


import com.aditya.banking.system.demo.entity.constant.TableName;
import com.aditya.banking.system.demo.entity.constant.enums.BankAccountStatus;
import com.aditya.banking.system.demo.entity.constant.enums.BankAccountType;
import com.aditya.banking.system.demo.entity.dao.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = TableName.BANK_ACCOUNT)
@Builder(toBuilder = true)
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount extends BaseEntity {

    private static final long serialVersionUID = -6251308367342483048L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_account_id")
    private Long id;

    @Column(unique = true)
    private Long number;

    private Long balance;
    private Double interestRate;
    private BankAccountType type;
    private BankAccountStatus status;
    private long bankBranchId;

}

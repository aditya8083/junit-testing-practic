package com.aditya.banking.system.demo.entity.dao;

import com.aditya.banking.system.demo.entity.constant.TableName;
import com.aditya.banking.system.demo.entity.constant.enums.BankStatus;
import com.aditya.banking.system.demo.entity.dao.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = TableName.BANK)
@Builder(toBuilder = true)
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Bank extends BaseEntity {

    private static final long serialVersionUID = -6251308367342483048L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String branchCode;
    @NonNull
    private String ifscCode;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String country;
    private BankStatus status;




}

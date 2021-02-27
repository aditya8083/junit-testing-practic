package com.aditya.banking.system.demo.entity.dao;


import com.aditya.banking.system.demo.entity.constant.TableName;
import com.aditya.banking.system.demo.entity.constant.enums.BankBranchStatus;
import com.aditya.banking.system.demo.entity.dao.common.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = TableName.BRANCH)
@Builder(toBuilder = true)
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Branch extends BaseEntity {

    private static final long serialVersionUID = -6251308367342483048L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_id")
    private Long id;

    @Column(unique = true)
    private String code;

    @Column(unique = true)
    private String ifscCode;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
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

    public void setId(Long id) {
        this.id = id;
    }
}

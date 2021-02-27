package com.aditya.banking.system.demo.entity.dao;

import com.aditya.banking.system.demo.entity.constant.TableName;
import com.aditya.banking.system.demo.entity.dao.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = TableName.CUSTOMER_ACCOUNT)
@Builder(toBuilder = true)
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerAccount extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_account_id")
    private Long id;

    @NonNull
    @Column(unique = true)
    private Long customerId;

    @NonNull
    @Column(unique = true)
    private Long bankId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }
}

package com.aditya.banking.system.demo.entity.dao;

import com.aditya.banking.system.demo.entity.constant.TableName;
import com.aditya.banking.system.demo.entity.constant.enums.CustomerStatus;
import com.aditya.banking.system.demo.entity.dao.common.BaseEntity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = TableName.CUSTOMER)
@Builder(toBuilder = true)
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity {

    private static final long serialVersionUID = -6251334536732483048L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String firstName;
    private String middleName;
    @NonNull
    private String lastName;
    @NonNull
    private String email;
    @NonNull
    private String contactNo;
    private Date dateOfBirth;
    private String kycInfo;
    private CustomerStatus status;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String country;


}

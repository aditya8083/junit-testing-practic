package com.aditya.banking.system.demo.entity.dao;

import com.aditya.banking.system.demo.entity.constant.TableName;
import com.aditya.banking.system.demo.entity.constant.enums.EmployeeStatus;
import com.aditya.banking.system.demo.entity.dao.common.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = TableName.EMPLOYEE)
@Builder(toBuilder = true)
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee extends BaseEntity {

    private static final long serialVersionUID = -6251334536732444048L;

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
    private boolean isAdmin;
    private Date dateOfBirth;
    private Date joinedDate;
    private Date lastDayOfWork;
    private EmployeeStatus status;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String country;

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}

package com.aditya.banking.system.demo.entity.dao.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1212006652955791818L;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    private String updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedDate;
    private String deletedBy;


}

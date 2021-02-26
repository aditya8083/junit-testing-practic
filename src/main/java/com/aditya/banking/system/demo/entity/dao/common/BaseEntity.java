package com.aditya.banking.system.demo.entity.dao.common;


import lombok.*;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1212006652955791818L;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    private Long createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    private Long updatedBy;

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }
}

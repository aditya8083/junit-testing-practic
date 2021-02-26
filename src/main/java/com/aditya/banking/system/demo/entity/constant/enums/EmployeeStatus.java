package com.aditya.banking.system.demo.entity.constant.enums;

public enum EmployeeStatus {

    ACTIVE("active"), BLOCKED("blocked"), INACTIVE("inactive");

    private String status;

    EmployeeStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

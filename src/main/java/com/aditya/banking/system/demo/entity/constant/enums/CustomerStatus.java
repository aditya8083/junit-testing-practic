package com.aditya.banking.system.demo.entity.constant.enums;

public enum CustomerStatus {

    ACTIVE("active"), BLOCKED("blocked"), INACTIVE("inactive");

    private String status;

    CustomerStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

package com.aditya.banking.system.demo.entity.constant.enums;

public enum AccountStatus {

    ACTIVE("active"), BLOCKED("blocked"), INACTIVE("inactive");

    private String status;

    AccountStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}

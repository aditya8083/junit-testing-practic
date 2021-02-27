package com.aditya.banking.system.demo.entity.constant.enums;

public enum BankAccountStatus {

    ACTIVE("active"), BLOCKED("blocked"), INACTIVE("inactive");

    private String status;

    BankAccountStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}

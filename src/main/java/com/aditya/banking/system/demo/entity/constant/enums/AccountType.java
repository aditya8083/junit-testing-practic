package com.aditya.banking.system.demo.entity.constant.enums;

public enum AccountType {

    SAVINGS("savings"), CURRENT("current"), LOAN("loan"), SALARY("salary");

    private String status;

    AccountType(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

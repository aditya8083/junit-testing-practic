package com.aditya.banking.system.demo.entity.constant.enums;

public enum BankAccountType {

    SAVINGS("savings"), CURRENT("current"), LOAN("loan"), SALARY("salary");

    private String status;

    BankAccountType(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

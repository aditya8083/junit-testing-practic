package com.aditya.banking.system.demo.entity.constant.enums;

public enum BankStatus {

    OPEN("open"), CLOSED("closed"), TEMPORARY_OUT_OF_ORDER("temporary_out_of_order");

    private String status;

    BankStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }


}

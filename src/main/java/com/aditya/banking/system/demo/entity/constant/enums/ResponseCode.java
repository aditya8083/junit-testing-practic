package com.aditya.banking.system.demo.entity.constant.enums;

public enum ResponseCode {

    SUCCESS("SUCCESS", "success"),
    SYSTEM_ERROR("SYSTEM_ERROR", "System Error: Contact our team"),
    EMPLOYEE_DOES_NOT_EXISTS("EMPLOYEE_DOES_NOT_EXISTS", " Employee does not exists in database"),
    DUPLICATE_REQUEST_BODY_FIELDS("DUPLICATE_REQUEST_BODY_FIELDS", "fields are not unique in request Body"),
    CUSTOMER_DOES_NOT_EXISTS("CUSTOMER_DOES_NOT_EXISTS", "Customer does not exists in database"),
    BRANCH_DOES_NOT_EXISTS("BRANCH_DOES_NOT_EXISTS", "Branch does not exists in database"),
    BANK_DOES_NOT_EXISTS("BANK_DOES_NOT_EXISTS", "Bank does not exists in database"),
    ACCOUNT_DOES_NOT_EXISTS("ACCOUNT_DOES_NOT_EXISTS", "Account does not exists in database"),
    BANK_ACCOUNT_NOT_FOUND("BANK_ACCOUNT_NOT_FOUND", "Bank Account not found"),
    TRANSFER_AMOUNT_GREATER("TRANSFER_AMOUNT_GREATER", "transfer amount is greater than amount in account"),
    ACCOUNT_ALREADY_EXISTS("ACCOUNT_ALREADY_EXISTS", "Account already exists in database"),
    NOT_AUTHORIZED_ERROR("NOT_AUTHORIZED_ERROR","Not authorized to do the operation"),
    REQUEST_MAPPING_EXCEPTION("REQUEST_MAPPING_EXCEPTION", "Request Mapping Exception");

    private String code;
    private String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

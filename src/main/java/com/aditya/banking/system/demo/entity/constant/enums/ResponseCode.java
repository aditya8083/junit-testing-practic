package com.aditya.banking.system.demo.entity.constant.enums;

public enum ResponseCode {

    SUCCESS("SUCCESS", "success"),
    SYSTEM_ERROR("SYSTEM_ERROR", "System Error: Contact our team"),
    DATA_NOT_EXIST("DATA_NOT_EXIST", "No data exist"),
    USER_DOES_NOT_EXISTS("USER_DOES_NOT_EXISTS", "User does not exists"),
    RUNTIME_ERROR("RUNTIME_ERROR", "Runtime Error"),
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

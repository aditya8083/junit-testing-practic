package com.aditya.banking.system.demo.configuration;

public class RequestMappingException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String code;
    private String message;

    public RequestMappingException(String code, String message) {
        super();
        this.setCode(code);
        this.setMessage(message);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "RequestMappingException{" + "code='" + code + '\'' + "} " + super.toString();
    }
}


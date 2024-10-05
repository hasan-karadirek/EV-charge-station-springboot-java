package com.sparkshare.demo.exception;

public class ApiException extends RuntimeException {
    private Integer statusCode;
    
    public ApiException(String message, Integer statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public Integer getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}

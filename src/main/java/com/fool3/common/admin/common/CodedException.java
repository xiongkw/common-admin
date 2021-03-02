package com.fool3.common.admin.common;

public class CodedException extends RuntimeException {
    private int code;

    public CodedException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
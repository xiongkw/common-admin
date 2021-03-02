package com.fool3.common.admin.exception;

import com.fool3.common.admin.common.CodedException;
import lombok.Getter;

public enum SecurityException {
    AUTH_FAILED_USER_NOT_FOUND(401001, "用户[%s]不存在"),
    AUTH_FAILED_USER_NOT_ENABLE(401002, "用户[%s]不可用"),
    AUTH_FAILED_INVALID_PASSWORD(401003, "用户名或密码错误"),

    ;

    @Getter
    private int code;

    @Getter
    private String msg;

    SecurityException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CodedException exception() {
        return new CodedException(this.code, this.msg);
    }

    public CodedException exception(Object... args) {
        String msg = String.format(this.msg, args);
        return new CodedException(this.code, msg);
    }
}
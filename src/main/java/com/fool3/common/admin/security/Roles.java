package com.fool3.common.admin.security;

public enum Roles {
    ADMIN("a"),
    COMMON("c");

    private String code;

    Roles(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

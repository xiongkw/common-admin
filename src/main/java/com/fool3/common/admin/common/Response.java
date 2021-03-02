package com.fool3.common.admin.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    private static final Response SUCCESS = new Response();

    private static final int CODE_SUCCESS = 0;

    private int code = CODE_SUCCESS;

    private String msg;

    private T data;

    public Response() {
    }

    private Response(T data) {
        this.data = data;
    }

    public static Response success() {
        return SUCCESS;
    }

    public static <T> Response<T> success(T data) {
        return new Response<T>(data);
    }

    public static Response fail(int code, String msg) {
        Response<Object> r = new Response<>();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return this.code == CODE_SUCCESS;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

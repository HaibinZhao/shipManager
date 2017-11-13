package com.waltcow.utility;
import lombok.Data;

@Data
public class WebResult<T> {

    private String code;
    private String msg;
    private String error;
    private T data;

    public static final String DEFAULT_SUCCESS_CODE = "S_OK";
    public static final String DEFAULT_SUCCESS_MSG = "操作成功";
    public static final String DEFAULT_FAILURE_CODE = "FA_UNKNOWN";
    public static final String DEFAULT_FAILURE_MSG = "操作失败";

    public WebResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public WebResult(String  code, String msg, String error) {
        this.code = code;
        this.msg = msg;
        this.error = error;
    }

    public WebResult(T data) {
        this.code = DEFAULT_SUCCESS_CODE;
        this.msg = DEFAULT_SUCCESS_MSG;
        this.data = data;
    }

    public static WebResult success(Object o) {
        return new WebResult(o);
    }

    public static WebResult success() {
        return new WebResult(DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MSG);
    }

    public static WebResult error(String code, String msg, String error) {
        return new WebResult(code, msg, error);
    }

    public static WebResult error(String error) {
        return new WebResult(DEFAULT_FAILURE_CODE, DEFAULT_FAILURE_MSG, error);
    }

    public static WebResult error(String code, String error) {
        return new WebResult(code, DEFAULT_FAILURE_MSG, error);
    }

    public static WebResult failure() {
        return new WebResult(DEFAULT_FAILURE_CODE, DEFAULT_FAILURE_MSG);
    }

    public static WebResult failure(String msg) {
        return new WebResult(DEFAULT_FAILURE_CODE, msg);
    }

    public static WebResult failure(String code, String msg) {
        return new WebResult(code, msg);
    }

}

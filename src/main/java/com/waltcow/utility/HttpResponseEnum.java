package com.waltcow.utility;

import lombok.Getter;

/**
 * @author reid
 * @Created 2017/8/2
 */
@Getter
public enum HttpResponseEnum {

    SC_OK("S_OK", "操作成功"),
    SC_BAD_REQUEST("FA_BAD_REQUEST", "错误请求"),
    SC_UNAUTHORIZED("FA_UNAUTHORIZED", "未登录或登录已超时"),

    SC_NOT_FOUND("FA_NOT_FOUND", "资源未找到"),
    SC_INTERNAL_SERVER_ERROR("FA_SERVER_ERROR", "服务器异常"),

    USER_ACCESS_DENIED("FA_USER_ACCESS_DENIED", "用户权限不够"),
    ARGUMENT_NOT_MATCH("FA_ERROR_PARAM_TYPE", "参数类型不匹配"),
    SIGN_UP_USER_EXIST("FA_USER_EXIST", "注册用户已存在"),
    SIGN_UP_EMAIL_EXIST("FA_EMAIL_EXIST", "注册邮箱已存在"),
    USER_NOT_EXIST("FA_USER_NOT_EXIST", "用户不存在"),
    PASSWORD_ERROR("FA_USER_PWD_NOT_MATCH", "用户密码错误");

    private String code;
    private String msg;

    HttpResponseEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

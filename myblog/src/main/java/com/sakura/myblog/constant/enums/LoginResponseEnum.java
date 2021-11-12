package com.sakura.myblog.constant.enums;

/**
 * @author Sakura
 */

public enum LoginResponseEnum {
    //
    USER_IS_EXIST(-1, "用户已存在"),
    PASSWORD_IS_ERROR(-2, "用户或密码错误"),
    USER_IS_NULL(-3, "用户不存在"),
    LOGIN_SUCCESS(1, "登录成功");

    private Integer code;
    private String msg;

    LoginResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

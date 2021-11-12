package com.sakura.myblog.constant.enums;

/**
 * @author Sakura
 */

public enum ResponseEnum {
    //
    UNKNOWN_ERROR(-1, "未知错误"),
    SUCCESS(10000, "成功"),
    USER_NOT_EXIST(1, "用户不存在"),
    USER_IS_EXISTS(2, "用户已存在"),
    DATA_IS_NULL(3, "数据为空"),
    DATA_IS_EXISTS(4, "数据已存在")
    ;
    private Integer code;
    private String msg;

    ResponseEnum(Integer code, String msg) {
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

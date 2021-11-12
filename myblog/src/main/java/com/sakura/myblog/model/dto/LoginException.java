package com.sakura.myblog.model.dto;

/**
 * @author Sakura
 */

public class LoginException extends RuntimeException {

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public LoginException() {
    }

    public LoginException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}

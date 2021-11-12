package com.sakura.myblog.controller;

import com.sakura.myblog.model.dto.BaseException;
import com.sakura.myblog.model.dto.LoginException;
import com.sakura.myblog.model.dto.ResponseDTO;
import com.sakura.myblog.utils.ResponseUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Sakura
 */
@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public ResponseDTO exceptionHandler(BaseException e) {
        return ResponseUtil.error(-1, e.getErrorMsg());

    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = LoginException.class)
    @ResponseBody
    public ResponseDTO exceptionHandler(LoginException e) {
        return ResponseUtil.error(e.getCode(), e.getMessage());
    }

}

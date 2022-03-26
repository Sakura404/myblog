package com.sakura.myblog.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Joe
 * createTime 2020/07/27 14:39
 * mail joe-code@foxmail.com
 */
@Controller
public class ErrorControllerImpl implements ErrorController {


    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")

    public void handleError(HttpServletRequest request) throws Throwable {
        if (request.getAttribute("javax.servlet.error.exception") != null) {
            throw (Throwable) request.getAttribute("javax.servlet.error.exception");
        }
    }
}
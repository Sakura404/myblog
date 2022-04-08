package com.sakura.myblog.controller;


import com.sakura.myblog.model.dto.ResponseDTO;
import com.sakura.myblog.model.entity.User;
import com.sakura.myblog.service.intf.UserService;
import com.sakura.myblog.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Sakura
 */
@RestController
@RequestMapping(value = "api/users")
public class UserController {
    UserService userservice;

    @Autowired
    public void setUserService(UserService userService) {
        this.userservice = userService;
    }
 
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO userLogin(@RequestBody User user, HttpServletResponse response) {
        return ResponseUtil.success(userservice.userLogin(user, response));
    }

    @RequestMapping(value = "/isLogin", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO isLogin(@RequestBody String token) {
        return ResponseUtil.success();
    }
}

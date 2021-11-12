package com.sakura.myblog.controller;


import com.sakura.myblog.model.dto.ResponseDTO;
import com.sakura.myblog.model.entity.User;
import com.sakura.myblog.service.intf.UserService;
import com.sakura.myblog.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ResponseDTO userLogin(@RequestBody User user) {
        return ResponseUtil.success(userservice.userLogin(user));
    }
}

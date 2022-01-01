package com.sakura.myblog.service.impl;

import com.sakura.myblog.constant.enums.LoginResponseEnum;
import com.sakura.myblog.mapper.UserMapper;
import com.sakura.myblog.model.dto.BaseException;
import com.sakura.myblog.model.dto.LoginException;
import com.sakura.myblog.model.entity.User;
import com.sakura.myblog.service.intf.UserService;
import com.sakura.myblog.utils.Md5Util;
import com.sakura.myblog.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Sakura
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {


    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public User addtourist(String name, String email) {
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        int addFlag = userMapper.addUser(user);
        if (addFlag != 1) {
            throw new BaseException("-1", "添加失败");
        }
        return user;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public Map<String, String> userLogin(User user) {
        User userByAccount = userMapper.findUserByAccount(user.getAccount());
        if (userByAccount == null) {
            throw new LoginException(LoginResponseEnum.USER_IS_NULL.getCode(), LoginResponseEnum.USER_IS_NULL.getMsg());
        }
        if (Md5Util.md5(user.getPassword()).equals(userByAccount.getPassword())) {
            Map<String, String> map = new HashMap<String, String>(1);
            String token = TokenUtil.createJWT(60 * 60 * 1000, user.getAccount());
            map.put("LOGIN_TOKEN", token);
            return map;
        } else {
            throw new LoginException(LoginResponseEnum.PASSWORD_IS_ERROR.getCode(), LoginResponseEnum.PASSWORD_IS_ERROR.getMsg());
        }
    }

}

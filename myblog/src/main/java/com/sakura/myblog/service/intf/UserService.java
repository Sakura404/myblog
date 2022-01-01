package com.sakura.myblog.service.intf;

import com.sakura.myblog.model.entity.User;

import java.util.Map;

/**
 * @author Sakura
 */
public interface UserService {
    public User addUser(User user);

    public User addtourist(String name,String email);

    public User updateUser(User user);

    public Map<String, String> userLogin(User user);
}

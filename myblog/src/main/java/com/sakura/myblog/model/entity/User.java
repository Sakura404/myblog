package com.sakura.myblog.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Sakura
 */
@Data
public class User {
    private int id;
    private String account;
    private String password;
    private String name;
    private Date created;
    private Date Modified;
    private String status;
    private String email;
}

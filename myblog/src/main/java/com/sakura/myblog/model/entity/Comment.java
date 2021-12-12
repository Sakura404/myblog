package com.sakura.myblog.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Sakura
 */
@Data
public class Comment {
    private int id;
    private int postId;
    private User user;
    private Comment reply;
    private String content;
    private String status;
    private Date date;
}

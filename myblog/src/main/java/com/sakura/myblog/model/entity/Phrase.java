package com.sakura.myblog.model.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Phrase {
    private int id;
    private String title;
    private String content;
    private String author;
    private Date date;
    private Date modified;
    private String status;
    private String color;
    private boolean dark;
}

package com.sakura.myblog.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Sakura
 * 媒体实体类
 */
@Data
public class Media {
    private int id;
    private String path;
    private String url;
    private String type;
    private double size;
    private Date date;
    private Date modified;
    private String excerpt;
}

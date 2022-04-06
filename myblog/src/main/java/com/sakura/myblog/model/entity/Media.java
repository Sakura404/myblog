package com.sakura.myblog.model.entity;

import lombok.Data;

import java.util.Date;
import java.util.Random;

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
    private String md5;

    public Media() {
        this.url = " https://cdn.jsdelivr.net/gh/Sakura404/myblog/src/assets/docsmall/" + (new Random().nextInt(15) + 1) + ".jpg";
    }


    public Media(String url) {
        this.url = url;
    }
}

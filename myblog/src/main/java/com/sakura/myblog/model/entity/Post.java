package com.sakura.myblog.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jdk.jfr.Timestamp;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 文章实体类
 *
 * @author Sakura
 */
@Data
public class Post implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String title;
    private String author;
    @Timestamp
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date date;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Timestamp
    private Date modified;
    private String content;
    private String excerpt;
    private String status;
    private Media attachment;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", date=" + date +
                ", modified=" + modified +
                ", content='" + content + '\'' +
                ", excerpt='" + excerpt + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

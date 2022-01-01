package com.sakura.myblog.model.VO;

import com.sakura.myblog.model.entity.Term;
import jdk.jfr.Timestamp;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author Sakura
 */
@Data
public class PostListVO {
    private Integer id;
    private String title;
    private String author;
    @Timestamp
    private Date date;
    @Timestamp
    private Date modified;
    private String content;
    private String excerpt;
    private List<Term> termList;
    private String status;
    private Integer commentCount;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public List<Term> getTermList() {
        return termList;
    }

    public void setTermList(List<Term> termList) {
        this.termList = termList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }
}

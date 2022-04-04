package com.sakura.myblog.model.VO;

import com.sakura.myblog.model.entity.Media;
import com.sakura.myblog.model.entity.Term;
import jdk.jfr.Timestamp;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Sakura
 */
@Data
public class PostListVO implements Serializable {
    private static final long serialVersionUID = 114514L;
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
    private Media attachment = new Media();
}

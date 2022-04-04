package com.sakura.myblog.model.VO;

import com.sakura.myblog.model.entity.Media;
import com.sakura.myblog.model.entity.Post;
import com.sakura.myblog.model.entity.Term;
import lombok.Data;

import java.util.List;

/**
 * @author Sakura
 */
@Data
public class PostTermVO {
    public List<Term> terms;
    public Post post;
    public Media media;
}

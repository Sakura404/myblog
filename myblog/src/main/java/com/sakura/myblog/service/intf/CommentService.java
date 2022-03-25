package com.sakura.myblog.service.intf;

import com.sakura.myblog.model.entity.Comment;

import java.util.List;
import java.util.Map;

/**
 * @author Sakura
 */
public interface CommentService {
    List<Comment> getComments(int postId);

    List<Comment> getComments();

    Comment findComment(int commentId);

    Comment addComment(Map<String, Object> comment);

    void deleteComment(int id);

    Comment updateComment(int id, Comment comment);
}

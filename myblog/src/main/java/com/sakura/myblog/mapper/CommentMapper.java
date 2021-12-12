package com.sakura.myblog.mapper;

import com.sakura.myblog.model.entity.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Select(value = "select comment_id,post_id,reply_id,comment_content,comment_status,comment_date " +
            "from mb_comments where post_id=#{postId}")
    @Results(id = "commentMap", value = {
            @Result(column = "comment_id", property = "id"),
            @Result(column = "post_id", property = "postId"),
            @Result(column = "comment_content", property = "content"),
            @Result(column = "comment_status", property = "status"),
            @Result(column = "comment_date", property = "date"),
            @Result(column = "reply_id", property = "reply", one = @One(select = "com.sakura.myblog.mapper.CommentMapper.findCommentsByCommentId"))
    })
    public List<Comment> findCommentsByPostId(int postId);

    @Select(value = "select comment_id,post_id,reply_id,comment_content,comment_status,comment_date " +
            "from mb_comments where comment_id=#{commentIndex}")
    @Results(id = "commentNullReplyMap", value = {
            @Result(column = "comment_id", property = "id"),
            @Result(column = "post_id", property = "postId"),
            @Result(column = "comment_content", property = "content"),
            @Result(column = "comment_status", property = "status"),
            @Result(column = "comment_date", property = "date"),
    })
    public Comment findCommentsByCommentId(int commentIndex);

    @Insert(value = "insert into mb_comments (post_id,reply_id,comment_content,comment_status,comment_date) values (postId,content,status,date )")
    public int addComment(Comment comment);
}

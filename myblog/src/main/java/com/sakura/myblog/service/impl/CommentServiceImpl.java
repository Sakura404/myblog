package com.sakura.myblog.service.impl;

import com.sakura.myblog.mapper.CommentMapper;
import com.sakura.myblog.mapper.UserMapper;
import com.sakura.myblog.model.dto.BaseException;
import com.sakura.myblog.model.entity.Comment;
import com.sakura.myblog.model.entity.User;
import com.sakura.myblog.service.intf.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Sakura
 */
//@CacheConfig(cacheNames = "comment")
@Service(value = "CommentService")
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    UserMapper userMapper;

//    @Cacheable(key = "#postId")
    @Override
    public List<Comment> getComments(int postId) {
        List<Comment> commentList = commentMapper.findCommentsByPostId(postId);
        if (commentList.isEmpty()) {
            throw new BaseException("404", "查询为空");
        }
        return commentList;
    }

    @Override
    public List<Comment> getComments() {
        List<Comment> commentList = commentMapper.getComments();
        if (commentList.isEmpty()) {
            throw new BaseException("404", "查询为空");
        }
        return commentList;
    }

    //    @Cacheable(key = "#commentId")
    @Override
    public Comment findComment(int commentId) {
        Comment comment = commentMapper.findCommentsByCommentId(commentId);
        if (comment == null) {
            throw new BaseException("404", "查询为空");
        }
        return comment;
    }

    @Override
    public Comment addComment(Map<String, Object> commentVO) {
        User user = userMapper.findUserByEmail(commentVO.get("email").toString());
        if (user == null) {
            user = new User();
            user.setName(commentVO.get("name").toString());
            user.setEmail(commentVO.get("email").toString());
            user.setStatus("tourist");
            user.setCreated(new Date());
            user.setModified(new Date());
            int addUserFlag = userMapper.addUser(user);
            if (addUserFlag != 1) {
                throw new BaseException("500", "添加失败");
            }
        }
       // System.out.println(user);
        Comment comment = new Comment();
        comment.setContent(commentVO.get("content").toString());
        comment.setUser(user);

        comment.setPostId(Integer.parseInt(commentVO.get("postId").toString()));
        comment.setStatus("public");
        comment.setDate(new Date());
        Comment reply = new Comment();
        if (commentVO.get("replyId") != null) {
            reply.setId(Integer.parseInt(commentVO.get("replyId").toString()));
            comment.setReply(reply);
        }

        int addFlag = commentMapper.addComment(comment);
        if (addFlag != 1) {
            throw new BaseException("500", "添加失败");
        }
        return comment;
    }

    @Override
    public void deleteComment(int id) {

    }

    @Override
    public Comment updateComment(int id, Comment comment) {
        return null;
    }
}

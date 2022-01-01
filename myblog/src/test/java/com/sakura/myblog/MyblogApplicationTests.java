package com.sakura.myblog;

import com.sakura.myblog.mapper.CommentMapper;
import com.sakura.myblog.mapper.PostMapper;
import com.sakura.myblog.mapper.TermMapper;
import com.sakura.myblog.mapper.UserMapper;
import com.sakura.myblog.model.entity.Comment;
import com.sakura.myblog.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class MyBlogApplicationTests {
    @Autowired
    private TermMapper termMapper;

    @Autowired
    private PostMapper postMapper;

    @Test
    void contextLoads() {
    }

    @Autowired
    private CommentMapper commentMapper;

    @Test
    void sd() {
        System.out.println(postMapper.findPostList());
        System.out.println(commentMapper.findCommentsByPostId(1));
    }

    @Test
    void Comment() {
        commentMapper.findCommentsByPostId(1);
        Comment asd = new Comment();
        asd.setContent("123");
        asd.setDate(new Date());
        asd.setStatus("public");
        asd.setPostId(1);
        User u = new User();
        u.setId(7);
        asd.setUser(u);
        commentMapper.addComment(asd);
    }

    @Autowired
    UserMapper userMapper;

    @Test
    void User() {
        System.out.println(userMapper.findUserByEmail("114514@xiabeize.com"));
    }
}

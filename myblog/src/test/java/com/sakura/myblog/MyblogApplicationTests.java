package com.sakura.myblog;

import com.sakura.myblog.mapper.CommentMapper;
import com.sakura.myblog.mapper.PostMapper;
import com.sakura.myblog.mapper.TermMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        System.out.println(commentMapper.findCommentsByPostId(1));
    }
}

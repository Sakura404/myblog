package com.sakura.myblog;

import com.sakura.myblog.mapper.*;
import com.sakura.myblog.model.entity.Comment;
import com.sakura.myblog.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
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

    @Value("${file.upload.path}")
    private String fileSavePath;
    @Autowired
    public MediaMapper mediaMapper;

    @Test
    void media() {
//        Media m = new Media();
//        m.setSize(12);
//        m.setPath("D:/vue/image");
//        m.setDate(new Date());
//        m.setModified(new Date());
//        m.setExcerpt("123123");
//        m.
//        mediaMapper.addMedia(m);
        System.out.println(mediaMapper.findAll());
        System.out.println(mediaMapper.deleteMedia(1));
        System.out.println(mediaMapper.findMediaById(1));
//        System.out.println(m);
    }

    @Test
    void file() {
        File file = new File(fileSavePath);
        File[] files = file.listFiles();
        for (File item : files) {
            System.out.println(item.getName());
        }
    }

    @Test
    void findPostListByTermId() {
        System.out.println(postMapper.findPostListByTermId(1));
    }
}

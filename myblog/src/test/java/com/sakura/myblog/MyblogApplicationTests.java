package com.sakura.myblog;

import com.sakura.myblog.mapper.PostMapper;
import com.sakura.myblog.mapper.TermMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class MyBlogApplicationTests {
    @Autowired
    private TermMapper termMapper;

    @Autowired
    private PostMapper postMapper;

    @Test
    void contextLoads() {
    }

}

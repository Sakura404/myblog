package com.sakura.myblog.controller;

import com.sakura.myblog.model.dto.ResponseDTO;
import com.sakura.myblog.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author Sakura
 */
@Controller
@RequestMapping(value = "/api/common")
public class CommonController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/findbeforafter/{id}")
    @ResponseBody
    public ResponseDTO findbeforafter(@PathVariable int id) {
        String sql = "WITH t AS (SELECT mb_posts.post_id,mb_posts.post_title,mb_posts.post_author,mb_posts.post_date,ROW_NUMBER() over ( ORDER BY post_date DESC ) AS rid FROM mb_posts )\n" +
                "SELECT post_id ,post_title,post_author,post_date,if(post_date>(SELECT post_date from mb_posts WHERE post_id=?),'PREVIOUS','NEXT') as type FROM t WHERE rid in ( SELECT rid - 1 FROM t WHERE post_id = ? ) or rid in (SELECT rid + 1  FROM\tt WHERE post_id = ?)";
        List<Map<String, Object>> r = jdbcTemplate.queryForList(sql, new Object[]{id, id, id});
        return ResponseUtil.success(r);
    }
}

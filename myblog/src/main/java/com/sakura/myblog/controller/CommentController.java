package com.sakura.myblog.controller;

import com.sakura.myblog.model.dto.ResponseDTO;
import com.sakura.myblog.service.intf.CommentService;
import com.sakura.myblog.service.intf.UserService;
import com.sakura.myblog.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Sakura
 */
@RestController
@RequestMapping(value = "/api/comments")
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseDTO addPosts(@RequestBody Map<String, Object> commentVO) {
        System.out.println(commentVO);
        commentService.addComment(commentVO);
        return ResponseUtil.success();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseDTO getCommentsByPostId(@PathVariable int id) {
        return ResponseUtil.success(commentService.getComments(id));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseDTO getComments() {
        return ResponseUtil.success(commentService.getComments());
    }

    @RequestMapping(value = "/{id}",method= RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public ResponseDTO deleteComment(@PathVariable int id) {
        if (commentService.deleteComment(id)) {
            return ResponseUtil.success();
        } else {
            return ResponseUtil.error(-430, "删除失败");
        }
    }
}


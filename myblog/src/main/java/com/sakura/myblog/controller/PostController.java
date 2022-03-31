package com.sakura.myblog.controller;

import com.sakura.myblog.model.VO.PostListVO;
import com.sakura.myblog.model.VO.PostTermVO;
import com.sakura.myblog.model.dto.ResponseDTO;
import com.sakura.myblog.service.intf.PostService;
import com.sakura.myblog.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Sakura
 */
@RestController
@RequestMapping(value = "/api/posts")
public class PostController {
    PostService postService;

    @Autowired
    public void setService(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseDTO findPost(@PathVariable int id) {
        return ResponseUtil.success(postService.findPost(id));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseDTO addPosts(@RequestBody PostTermVO postTermVO) {
        return ResponseUtil.success(postService.addPost(postTermVO));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public ResponseDTO deletePost(@PathVariable int id) {
        postService.deletePost(id);
        return ResponseUtil.success();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
    @ResponseBody
    public ResponseDTO updatePost(@PathVariable int id, @RequestBody PostTermVO postTermVO) {

        return ResponseUtil.success(postService.updatePost(id, postTermVO));
    }


    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseDTO<List<PostListVO>> getPost() {
        return ResponseUtil.success(postService.getPost());
    }
//
//    @RequestMapping(value = "/{pageNum}/{pageSize}", method = RequestMethod.GET, produces = "application/json")
//    @ResponseBody
//    public ResponseDTO<List<PostListVO>> getPostPages(@PathVariable int pageNum, @PathVariable int pageSize) {
//        return ResponseUtil.success(postService.getPost(pageNum,pageSize));
//    }


    @RequestMapping(value = "/term/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseDTO<List<PostListVO>> getPostByTermId(@PathVariable int id) {
        return ResponseUtil.success(postService.getPostByTermId(id));
    }

    @RequestMapping(value = "/term/", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseDTO<List<PostListVO>> getPostWithoutTerm() {
        return ResponseUtil.success(postService.getPostWithoutTerm());
    }
}

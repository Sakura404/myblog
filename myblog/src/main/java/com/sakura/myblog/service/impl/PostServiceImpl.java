package com.sakura.myblog.service.impl;


import com.sakura.myblog.mapper.PostMapper;
import com.sakura.myblog.mapper.TermMapper;
import com.sakura.myblog.model.VO.PostListVO;
import com.sakura.myblog.model.VO.PostTermVO;
import com.sakura.myblog.model.dto.BaseException;
import com.sakura.myblog.model.entity.Post;
import com.sakura.myblog.model.entity.Term;
import com.sakura.myblog.service.intf.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author Sakura
 */
@Service(value = "postService")
public class PostServiceImpl implements PostService {
    private TermMapper termMapper;
    private PostMapper postMapper;

    @Autowired
    public void setTermMapper(TermMapper termMapper) {
        this.termMapper = termMapper;
    }

    @Autowired
    public void setUserDao(PostMapper postMapper) {
        this.postMapper = postMapper;
    }


    @Override
    public List<PostListVO> getPost() {
        List<PostListVO> postList = postMapper.findPostList();
        if (postList.isEmpty()) {
            throw new BaseException("-1", "空查询");
        }
        return postList;
    }

    @Override
    public Post findPost(int id) {
        Post post = postMapper.findPostById(id);
        if (post == null) {
            throw new BaseException("-1", "空查询2");
        }
        return post;
    }

    @Override
    public Post addPost(PostTermVO postTermVO) {
        Post post = postTermVO.getPost();
        List<Term> termList = postTermVO.getTerms();
        post.setModified(new Date(System.currentTimeMillis()));

        int addFlag = postMapper.addPost(post);
        if (addFlag != 1) {
            throw new BaseException("-1", "添加失败");
        }
        Iterator<Term> i = termList.iterator();
        while (i.hasNext()) {
            postMapper.setTermRelationships(i.next().getId(), post.getId());
        }
        return post;
    }

    @Override
    public void deletePost(int id) {
        postMapper.deleteTermRelationShipsByPostId(id);
        int deleteFlag = postMapper.deletePostById(id);
        if (deleteFlag != 1) {
            throw new BaseException("-1", "删除失败");
        }

        return;
    }

    @Override
    public Post updatePost(int id, PostTermVO postTermVO) {
        Post post = postTermVO.getPost();
        post.setId(id);
        post.setModified(new Date(System.currentTimeMillis()));
        System.out.println(post.getDate());
        int updateFlag = postMapper.updatePost(post);
        if (updateFlag != 1) {
            throw new BaseException("-1", "更新失败");
        }
        List<Term> termListNew = postTermVO.getTerms();
        List<Term> termList = postTermVO.getTerms();
        List<Term> termListOld = termMapper.findTermByPostId(post.getId());
        termListOld.removeAll(termList);
        Iterator<Term> iOld = termListOld.iterator();
        while (iOld.hasNext()) {
            postMapper.deleteTermRelationShips(iOld.next().getId(), post.getId());
        }
        termListNew.removeAll(termMapper.findTermByPostId(post.getId()));
        Iterator<Term> iNew = termListNew.iterator();
        while (iNew.hasNext()) {
            postMapper.setTermRelationships(iNew.next().getId(), post.getId());
        }

        return post;
    }
}

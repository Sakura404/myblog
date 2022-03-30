package com.sakura.myblog.service.intf;

import com.github.pagehelper.PageInfo;
import com.sakura.myblog.model.VO.PostListVO;
import com.sakura.myblog.model.VO.PostTermVO;
import com.sakura.myblog.model.entity.Post;

import java.util.List;

/**
 * @author Sakura
 */
public interface PostService {
    /**
     * getPost
     * 返回全部文章含标签的列表
     *
     * @return
     */


    List<PostListVO> getPost();

    List<PostListVO> getPostByTermId(int id);

    PageInfo getPost(int pageNum, int pageSize);

    /**
     * findPost
     * <p>
     * 根据传入id查找文章
     *
     * @param id 文章id
     * @return post
     */
    Post findPost(int id);

    /**
     * addPost
     *
     * @param postTermVO 文章实体
     * @return post
     */
    Post addPost(PostTermVO postTermVO);

    /**
     * deletePost
     *
     * @param id
     */
    void deletePost(int id);

    /**
     * updatePost
     *
     * @param postTermVO
     * @return
     */
    Post updatePost(int id,PostTermVO postTermVO);
}

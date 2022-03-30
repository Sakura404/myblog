package com.sakura.myblog.mapper;

import com.sakura.myblog.model.VO.PostListVO;
import com.sakura.myblog.model.entity.Post;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Sakura
 */
@Mapper
public interface PostMapper {
    /**
     * find Post ById
     *
     * @param id
     * @return Post
     */
    @Select("select post_id,post_title,post_author,post_date,post_modified,post_content,post_excerpt,post_status,post_attachment from mb_posts where post_id=#{id} ")
    @Results(id = "PostMap", value = {
            @Result(column = "post_id", property = "id"),
            @Result(column = "post_title", property = "title"),
            @Result(column = "post_author", property = "author"),
            @Result(column = "post_date", property = "date"),
            @Result(column = "post_modified", property = "modified"),
            @Result(column = "post_content", property = "content"),
            @Result(column = "post_excerpt", property = "excerpt"),
            @Result(column = "post_attachment", property = "attachment", one = @One(select = "com.sakura.myblog.mapper.MediaMapper.findMediaById")),
            @Result(column = "post_status", property = "status")
    })
    Post findPostById(int id);

    @Select("SELECT\n" +
            "\t* \n" +
            "FROM\n" +
            "\tmb_posts\n" +
            "\tleft JOIN mb_term_relationships \n" +
            "ON\n" +
            "\tmb_posts.post_id = mb_term_relationships.post_id \n" +
            "WHERE\n" +
            "\tmb_term_relationships.term_id =#{id}")
    @ResultMap(value = "PostListMap")
    List<PostListVO> findPostListByTermId(int id);

    /**
     * deleteTermRelationShipsByPostId
     *
     * @param id
     * @return
     */
    @Delete(value = "delete from mb_term_relationships where post_id=#{id}")
    int deleteTermRelationShipsByPostId(int id);

    /**
     * setTermRelationships
     *
     * @param termId
     * @param postId
     * @return
     */
    @Insert(value = "insert into mb_term_relationships( term_id,post_id) values (#{termId},#{postId})")
    int setTermRelationships(int termId, int postId);

    /**
     * deleteTermRelationShips
     *
     * @param termId
     * @param postId
     * @return
     */
    @Delete(value = "delete from mb_term_relationships where term_id=#{termId} and post_id=#{postId}")
    int deleteTermRelationShips(int termId, int postId);

    /**
     * findPostList
     *
     * @return
     */
    @Select("SELECT\n" +
            "\tmb_posts.post_id AS post_id,\n" +
            "\tpost_title,\n" +
            "\tpost_author,\n" +
            "\tpost_date,\n" +
            "\tpost_modified,\n" +
            "\tpost_excerpt,\n" +
            "\tCOUNT( mb_comments.post_id ) AS post_commentCount,\n" +
            "\tpost_status,\n" +
            "\tpost_attachment \n" +
            "FROM\n" +
            "\tmb_posts\n" +
            "\tLEFT JOIN mb_comments ON mb_posts.post_id = mb_comments.post_id \n" +
            "GROUP BY\n" +
            "\tmb_posts.post_id \n" +
            "ORDER BY\n" +
            "\t`post_date` DESC")
    @Results(id = "PostListMap", value = {
            @Result(column = "post_id", property = "id"),
            @Result(column = "post_title", property = "title"),
            @Result(column = "post_author", property = "author"),
            @Result(column = "post_date", property = "date"),
            @Result(column = "post_modified", property = "modified"),
            @Result(column = "post_excerpt", property = "excerpt"),
            @Result(column = "post_status", property = "status"),
            @Result(column = "post_commentCount",property="commentCount"),
            @Result(column = "post_attachment", property = "attachment", one = @One(select = "com.sakura.myblog.mapper.MediaMapper.findMediaById")),
            @Result(column = "post_id", property = "termList", many = @Many(select = "com.sakura.myblog.mapper.TermMapper.findTermByPostId"))
    })
    List<PostListVO> findPostList();

    /**
     * addPost
     *
     * @param post
     * @return int
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into mb_posts " +
            "(post_title,post_author,post_date,post_modified,post_content,post_excerpt,post_status,post_attachment) " +
            "values (#{title},#{author},#{date},#{modified},#{content},#{excerpt},#{status},#{attachment.id})")
    int addPost(Post post);

    /**
     * updatePost
     *
     * @param post
     * @return int
     */
    @Update(value = "update mb_posts set post_title=#{title}, post_author=#{author},post_date=#{date}, post_modified=#{modified},post_content=#{content},post_excerpt=#{excerpt},post_status=#{status} ,post_attachment=#{attachment.id} where post_id =#{id}")
    int updatePost(Post post);

    /**
     * deletePostById
     *
     * @param id
     * @return int
     */
    @Delete(value = "delete from mb_posts where post_id=#{id}")
    int deletePostById(int id);


}

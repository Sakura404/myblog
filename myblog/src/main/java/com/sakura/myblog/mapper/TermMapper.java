package com.sakura.myblog.mapper;

import com.sakura.myblog.model.entity.Term;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Sakura
 */
@Mapper
public interface TermMapper {
    /**
     * findAll
     *
     * @return List<Term>
     */
    @Select(value = "select term_id , term_name , term_status from mb_terms")
    @Results(id = "termMap", value = {
            @Result(column = "term_id", property = "id"),
            @Result(column = "term_name", property = "name"),
            @Result(column = "term_status", property = "status")
    })
    List<Term> findAll();

    /**
     * findTermById
     *
     * @param id
     * @return
     */
    @Select(value = "select term_id , term_name , term_status from mb_terms where term_id=#{id}")
    @ResultMap(value = "termMap")
    Term findTermById(int id);

    /**
     * findTermById
     *
     * @param id
     * @return
     */
    @Select(value = "SELECT t.term_id,t.term_name ,t.term_status from mb_term_relationships as r INNER JOIN mb_terms as t ON r.term_id=t.term_id WHERE r.post_id=#{id}")
    @ResultMap(value = "termMap")
    List<Term> findTermByPostId(int id);

    /**
     * addTerm
     *
     * @param term
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert(value = "insert into mb_terms (term_name , term_status) values (#{name},#{status})")
    int addTerm(Term term);



    /**
     * updateTerm
     *
     * @param term
     * @return
     */
    @Update(value = "update mb_terms set term_name=#{name},term_status=#{status} where term_id=#{id}")
    int updateTerm(Term term);

    /**
     * deleteTerm
     *
     * @param id
     * @return
     */
    @Delete(value = "Delete from mb_terms where term_id=#{id}")
    int deleteTerm(int id);
}

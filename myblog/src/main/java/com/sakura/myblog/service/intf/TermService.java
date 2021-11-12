package com.sakura.myblog.service.intf;

import com.sakura.myblog.model.entity.Term;

import java.util.List;

/**
 * @author Sakura
 */
public interface TermService {
    /**
     * getTerm
     * 获取全部标签
     *
     * @return
     */
    List<Term> getTerm();

    /**
     * findTerm
     *
     * @param id
     * @return
     */
    Term findTerm(int id);

    /**
     * addTerm
     * 增加标签
     *
     * @param term
     * @return
     */
    Term addTerm(Term term);

    /**
     * deleteTerm
     *
     * @param id
     */
    void deleteTerm(int id);

    /**
     * findTermByPostId
     *
     * @param id
     * @return
     */
    List<Term> findTermByPostId(int id);
}

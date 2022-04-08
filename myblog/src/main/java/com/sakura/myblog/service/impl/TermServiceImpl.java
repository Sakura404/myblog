package com.sakura.myblog.service.impl;

import com.sakura.myblog.mapper.TermMapper;
import com.sakura.myblog.model.dto.BaseException;
import com.sakura.myblog.model.entity.Term;
import com.sakura.myblog.service.intf.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sakura
 */
@Service(value = "TermService")
public class TermServiceImpl implements TermService {
    TermMapper termMapper;

    @Autowired
    public void setTermMapper(TermMapper termMapper) {
        this.termMapper = termMapper;
    }


    @Override
    public List<Term> getTerm() {
        List<Term> termList = termMapper.findAll();
        if (termList.isEmpty()) {
            throw new BaseException("404", "查询为空");

        }
        return termList;
    }

    @Override
    public Term findTerm(int id) {
        Term term = termMapper.findTermById(id);
        if (term == null) {
            throw new BaseException("404", "id为" + id + "的标签不存在");
        }
        return term;
    }

    @Override
    public Term addTerm(Term term) {
        term.setStatus("public");
        int addFlag = termMapper.addTerm(term);
        if (addFlag != 1) {
            throw new BaseException("500", "添加失败");
        }
        return term;
    }

    @Override
    public void deleteTerm(int id) {
        int deleteFlag = termMapper.deleteTerm(id);
        if (deleteFlag != 1) {
            throw new BaseException("500", "删除失败");
        }
    }

    @Override
    public List<Term> findTermByPostId(int id) {
        List<Term> termList = termMapper.findTermByPostId(id);
        if (termList.isEmpty()) {
            throw new BaseException("404", "空查询");
        }
        return termList;
    }
}

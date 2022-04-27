package com.sakura.myblog.service.impl;

import com.sakura.myblog.mapper.PhraseMapper;
import com.sakura.myblog.model.dto.BaseException;
import com.sakura.myblog.model.entity.Phrase;
import com.sakura.myblog.service.intf.PhraseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Sakura
 */
@Service
public class PhraseServiceImpl implements PhraseService {
    PhraseMapper phraseMapper;

    @Autowired
    public void setPhraseMapper(PhraseMapper phraseMapper) {
        this.phraseMapper = phraseMapper;
    }

    @Override
    public List<Phrase> getPhrase() {
        List<Phrase> phraseList = phraseMapper.getAll();
        if (phraseList.isEmpty()) {
            throw new BaseException("404", "查询为空");
        }
        return phraseList;
    }

    @Override
    public Phrase findPhraseById(int id) {
        Phrase phrase = phraseMapper.findPhraseById(id);
        if (phrase == null) {
            throw new BaseException("404", "查询为空");
        }
        return phrase;
    }

    @Override
    public boolean deletePhrase(int id) {
        int flag = phraseMapper.deletePhrase(id);
        if (flag != 1) {
            throw new BaseException("500", "删除失败");
        }
        return true;
    }

    @Override
    public Phrase updatePhrase(Phrase phrase) {
        phrase.setModified(new Date());
        if (phraseMapper.updatePhrase(phrase) == 0) {
            throw new BaseException("500", "更新失败");
        }
        return phrase;
    }

    @Override
    public Phrase addPhrase(Phrase phrase) {
        phrase.setDate(new Date());
        phrase.setModified(new Date());
        phrase.setStatus("public");
        int addFlag = phraseMapper.addPhrase(phrase);
        if (addFlag != 1) {
            throw new BaseException("500", "添加失败");
        }
        return phrase;
    }
}

package com.sakura.myblog.mapper;

import com.sakura.myblog.model.entity.Phrase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Sakura
 */
@Mapper
public interface PhraseMapper {
    @Select("select * from mb_phrase where not phrase_status = 'delete' ORDER BY phrase_date DESC")
    List<Phrase> getAll();

    Phrase getPhraseById(int id);

    int addPhrase(Phrase phrase);

    int deletePhrase(int id);

    int update(int id, Phrase phrase);
}

package com.sakura.myblog.mapper;

import com.sakura.myblog.model.entity.Phrase;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Sakura
 */
@Mapper
public interface PhraseMapper {
    @Select("select * from mb_phrase where not phrase_status = 'delete' ORDER BY phrase_date DESC")
    @Results(id = "PhraseMap", value = {
            @Result(column = "phrase_id", property = "id"),
            @Result(column = "phrase_title", property = "title"),
            @Result(column = "phrase_content", property = "content"),
            @Result(column = "phrase_author", property = "author"),
            @Result(column = "phrase_date", property = "date"),
            @Result(column = "phrase_modified", property = "modified"),
            @Result(column = "phrase_status", property = "status"),
            @Result(column = "phrase_color", property = "color"),
            @Result(column = "phrase_dark", property = "dark")
    })
    List<Phrase> getAll();

    @Select("select * from mb_phrase where not phrase_status = 'delete' and phrase_id=#{id}")
    @ResultMap(value = "PhraseMap")
    Phrase findPhraseById(int id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into mb_phrase (phrase_title,phrase_content,phrase_author,phrase_date,phrase_modified,phrase_status,phrase_color,phrase_dark) " +
            "values " +
            "(#{title},#{content},#{author},#{date},#{modified},#{status},#{color},#{dark})")
    int addPhrase(Phrase phrase);

    @Update("update mb_phrase set phrase_title=#{title},phrase_content=#{content},phrase_author=#{author}," +
            "phrase_modified=#{modified},phrase_status=#{status},phrase_color=#{color},phrase_dark=#{dark} where phrase_id=#{id}")
    int updatePhrase(Phrase phrase);

    @Update("update mb_phrase set phrase_status='delete' where phrase_id=#{id}")
    int deletePhrase(int id);
}

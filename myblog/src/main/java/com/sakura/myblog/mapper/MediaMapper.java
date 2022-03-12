package com.sakura.myblog.mapper;

import com.sakura.myblog.model.entity.Media;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MediaMapper {
    @Select(value = "select media_id,media_path,media_url,media_type,media_size,media_date,media_modified,media_excerpt from mb_medias")
    @Results(id = "mediaMap", value = {
            @Result(column = "media_id", property = "id"),
            @Result(column = "media_path", property = "path"),
            @Result(column = "media_url", property = "url"),
            @Result(column = "media_type", property = "type"),
            @Result(column = "media_size", property = "size"),
            @Result(column = "media_date", property = "date"),
            @Result(column = "media_modified", property = "modified"),
            @Result(column = "media_excerpt", property = "excerpt")
    })
    public List<Media> findAll();

    @Select(value = "select media_id,media_path,media_url,media_type,media_size,media_date,media_modified,media_excerpt from mb_medias where media_id=#{id}")
    @ResultMap(value = "mediaMap")
    public Media findMediaById(int id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert(value = "insert into mb_medias (media_path,media_url,media_type,media_size,media_date,media_modified,media_excerpt) values (#{path},#{url},#{type},#{size},#{date},#{modified},#{excerpt})")
    public int addMedia(Media media);

    @Delete(value = "delete from mb_medias where media_id=#{id}")
    public int deleteMedia(int id);

    @Update(value = "update mb_medias set media_path=#{path},media_url=#{url},media_type=#{type},media_size=#{size},media_date=#{date},media_modified=#{modified},media_excerpt=#{excerpt} where media_id=#{id}")
    @ResultMap(value = "mediaMap")
    public Media updateMedia(Media media);
}

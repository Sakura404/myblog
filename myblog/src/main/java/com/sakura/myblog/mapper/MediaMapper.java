package com.sakura.myblog.mapper;

import com.sakura.myblog.model.entity.Media;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MediaMapper {
    @Select(value = "select media_id,media_path,media_url,media_type,media_size,media_date,media_modified,media_excerpt,media_md5 from mb_medias")
    @Results(id = "mediaMap", value = {
            @Result(column = "media_id", property = "id"),
            @Result(column = "media_path", property = "path"),
            @Result(column = "media_url", property = "url"),
            @Result(column = "media_type", property = "type"),
            @Result(column = "media_size", property = "size"),
            @Result(column = "media_date", property = "date"),
            @Result(column = "media_modified", property = "modified"),
            @Result(column = "media_excerpt", property = "excerpt"),
            @Result(column = "media_md5", property = "md5")
    })
    List<Media> findAll();

    @Select(value = "select media_id,media_path,media_url,media_type,media_size,media_date,media_modified,media_excerpt,media_md5 from mb_medias where media_id=#{id}")
    @ResultMap(value = "mediaMap")
    Media findMediaById(int id);

    @Select(value = "select media_id from mb_medias where media_md5=#{md5}")
    @ResultMap(value = "mediaMap")
    Media findMediaByMd5(String md5);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert(value = "insert into mb_medias (media_path,media_url,media_type,media_size,media_date,media_modified,media_excerpt,media_md5) values (#{path},#{url},#{type},#{size},#{date},#{modified},#{excerpt},#{md5})")
    int addMedia(Media media);

    @Delete(value = "delete from mb_medias where media_id=#{id}")
    int deleteMedia(int id);

    @Update(value = "update mb_medias set media_modified=#{modified},media_excerpt=#{excerpt} where media_id=#{id}")
    int updateMedia(Media media);
}

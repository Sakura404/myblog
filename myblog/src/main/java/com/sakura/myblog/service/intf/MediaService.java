package com.sakura.myblog.service.intf;

import com.sakura.myblog.model.entity.Media;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MediaService {
    List<Media> getMedia();

    Media findMediaById(int id);

    void deleteMedia(int id);

    Media updateMedia(Media media);

    Media addMedia(MultipartFile file);
}

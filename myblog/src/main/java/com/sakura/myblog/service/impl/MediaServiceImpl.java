package com.sakura.myblog.service.impl;

import com.sakura.myblog.mapper.MediaMapper;
import com.sakura.myblog.model.dto.BaseException;
import com.sakura.myblog.model.entity.Media;
import com.sakura.myblog.service.intf.MediaService;
import com.sakura.myblog.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class MediaServiceImpl implements MediaService {

    MediaMapper mediaMapper;
    @Value("${file.upload.path}")
    private String fileSavePath;

    @Value("${file.coming.path}")
    private String fileSaveUrl;

    @Autowired
    public void setMediaMapper(MediaMapper mediaMapper) {
        this.mediaMapper = mediaMapper;
    }

    @Override
    public List<Media> getMedia() {
        List<Media> MediaList = mediaMapper.findAll();
        if (MediaList.isEmpty()) {
            throw new BaseException("-1", "查询为空");
        }
        return MediaList;
    }

    @Override
    public Media findMediaById(int id) {
        Media Media = mediaMapper.findMediaById(id);
        if (Media == null) {
            throw new BaseException("-1", "id为" + id + "的媒体不存在");
        }
        return Media;
    }

    @Override
    public void deleteMedia(int id) {

    }

    @Override
    public Media updateMedia(Media media) {
        return null;
    }

    @Override
    public Media addMedia(MultipartFile file) {
        String filePath = "";
        String fileUrl = "";
        String originalFilename = file.getOriginalFilename();
        File fileSrc = new File(fileSavePath);
        if (!fileSrc.exists() && !fileSrc.isDirectory()) {
            fileSrc.mkdirs();
        }
        long time = System.currentTimeMillis();
        filePath = new StringBuilder(fileSavePath)
                .append(time)
                .append(originalFilename)
                .toString();
        fileUrl = new StringBuilder(fileSaveUrl)
                .append(time)
                .append(originalFilename)
                .toString();
        Media media = new Media();
        media.setPath(filePath);
        media.setSize((double) file.getSize());
        media.setType(file.getContentType());
        media.setModified(new Date());
        media.setDate(new Date());
        media.setUrl(fileUrl);
        try {
            file.transferTo(new File(filePath));
        } catch (IOException e) {
            ResponseUtil.error(-10000, "上传失败");
        }
        int addFlag = 0;
        addFlag = mediaMapper.addMedia(media);
        if (addFlag == 0) {
            throw new BaseException("-1", "添加失败");
        }
        return media;
    }
}
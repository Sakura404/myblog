package com.sakura.myblog.service.impl;

import com.sakura.myblog.mapper.MediaMapper;
import com.sakura.myblog.model.dto.BaseException;
import com.sakura.myblog.model.entity.Media;
import com.sakura.myblog.service.intf.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
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
            throw new BaseException("404", "查询为空");
        }
        Iterator<Media> m = MediaList.iterator();
        while (m.hasNext()) {
            Media item = m.next();
            File file = new File(item.getPath());
            if (!file.exists()) {
                mediaMapper.deleteMedia(item.getId());
                m.remove();
            }
        }
        return MediaList;
    }

    @Override
    public Media findMediaById(int id) {
        Media Media = mediaMapper.findMediaById(id);
        if (Media == null) {
            throw new BaseException("404", "id为" + id + "的媒体不存在");
        }
        return Media;
    }

    @Override
    public boolean deleteMedia(int id) {
        Media m = mediaMapper.findMediaById(id);
        File file = new File(m.getPath());
        if (file.exists()) {
            if (file.delete()) {
                mediaMapper.deleteMedia(id);
            }
        }
        return true;
    }

    @Override
    public Media updateMedia(Media media) {
        media.setModified(new Date());
        if (mediaMapper.updateMedia(media) == 0) {
            throw new BaseException("500", "更新失败");
        }
        return media;
    }

    @Override
    public Media addMedia(MultipartFile file) {
        String filePath = "";
        String fileUrl = "";
        String md5 = null;
        try {
            md5 = org.springframework.util.DigestUtils.md5DigestAsHex(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (mediaMapper.findMediaByMd5(md5) != null) {
            throw new BaseException("500", "文件已存在");
        }

        File fileSrc = new File(fileSavePath);
        if (!fileSrc.exists() && !fileSrc.isDirectory()) {
            fileSrc.mkdirs();
        }
        long time = System.currentTimeMillis();
        filePath = new StringBuilder(fileSavePath)
                .append(time)
                .toString();
        fileUrl = new StringBuilder(fileSaveUrl)
                .append(time)
                .toString();
        Media media = new Media();
        media.setPath(filePath);
        media.setSize((double) file.getSize());
        media.setType(file.getContentType());
        media.setModified(new Date());
        media.setDate(new Date());
        media.setUrl(fileUrl);
        media.setMd5(md5);
        try {
            file.transferTo(new File(filePath));
        } catch (IOException e) {
            throw new BaseException("500", "上传失败");
        }
        int addFlag = 0;
        addFlag = mediaMapper.addMedia(media);
        if (addFlag == 0) {
            throw new BaseException("500", "添加失败");
        }
        return media;
    }
}

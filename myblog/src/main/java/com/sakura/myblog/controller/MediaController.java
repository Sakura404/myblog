package com.sakura.myblog.controller;

import com.sakura.myblog.model.dto.ResponseDTO;
import com.sakura.myblog.service.intf.MediaService;
import com.sakura.myblog.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/Medias")
public class MediaController {
    MediaService mediaService;

    @Autowired
    public void setService(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO addPosts(@RequestParam("file") MultipartFile file) {
        return ResponseUtil.success(mediaService.addMedia(file));
    }
}

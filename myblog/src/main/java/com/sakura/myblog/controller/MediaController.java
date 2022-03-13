package com.sakura.myblog.controller;

import com.sakura.myblog.model.dto.ResponseDTO;
import com.sakura.myblog.model.entity.Media;
import com.sakura.myblog.service.intf.MediaService;
import com.sakura.myblog.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Sakura
 */
@RestController
@RequestMapping(value = "/api/medias")
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDTO getMedia() {
        return ResponseUtil.success(mediaService.getMedia());
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json")
    @ResponseBody
    public ResponseDTO updateMedia(@RequestBody Media media) {
        return ResponseUtil.success(mediaService.updateMedia(media));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseDTO deleteMedia(@RequestBody int i) {
        if (mediaService.deleteMedia(i)) {
            return ResponseUtil.success();
        } else {
            return ResponseUtil.error(-600, "删除失败");
        }
    }
}

package com.sakura.myblog.controller;

import com.sakura.myblog.model.dto.ResponseDTO;
import com.sakura.myblog.model.entity.Phrase;
import com.sakura.myblog.service.intf.PhraseService;
import com.sakura.myblog.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/phrases")
public class PhraseController {
    PhraseService phraseService;

    @Autowired
    public void setService(PhraseService phraseService) {
        this.phraseService = phraseService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO addPhrase(@RequestBody Phrase phrase) {
        return ResponseUtil.success(phraseService.addPhrase(phrase));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDTO getPhrase() {
        return ResponseUtil.success(phraseService.getPhrase());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDTO findPhrase(@PathVariable int id) {
        return ResponseUtil.success(phraseService.findPhraseById(id));
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json")
    @ResponseBody
    public ResponseDTO updatePhrase(@RequestBody Phrase phrase) {
        return ResponseUtil.success(phraseService.updatePhrase(phrase));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseDTO deletePhrase(@PathVariable int id) {
        if (phraseService.deletePhrase(id)) {
            return ResponseUtil.success();
        } else {
            return ResponseUtil.error(-430, "删除失败");
        }
    }
}

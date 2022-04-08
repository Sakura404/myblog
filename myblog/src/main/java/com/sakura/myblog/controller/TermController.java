package com.sakura.myblog.controller;

import com.sakura.myblog.model.dto.ResponseDTO;
import com.sakura.myblog.model.entity.Term;
import com.sakura.myblog.service.intf.TermService;
import com.sakura.myblog.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sakura
 */
@RestController
@RequestMapping(value = "/api/terms")
public class TermController {
    TermService termService;

    @Autowired
    public void setTermService(TermService termService) {
        this.termService = termService;
    }

    @GetMapping(value = "/")
    @ResponseBody
    public ResponseDTO getTerms() {
        return ResponseUtil.success(termService.getTerm());
    }

    @GetMapping(value = "/post/{id}")
    @ResponseBody
    public ResponseDTO findTermByPostId(@PathVariable int id) {
        return ResponseUtil.success(termService.findTermByPostId(id));
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseDTO findTerm(@PathVariable int id) {
        return ResponseUtil.success(termService.findTerm(id));
    }

    @PostMapping(value = "/")
    @ResponseBody
    public ResponseDTO addTerm(@RequestBody Term term) {
        return ResponseUtil.success(termService.addTerm(term));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public ResponseDTO deleteTerm(@PathVariable int id) {
        termService.deleteTerm(id);
        return ResponseUtil.success();
    }
}

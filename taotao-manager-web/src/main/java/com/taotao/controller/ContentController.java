package com.taotao.controller;

import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @RequestMapping("/add")
    @ResponseBody
    public TaotaoResult addContent(TbContent tbContent) {


        TaotaoResult taotaoResult = contentService.addContent(tbContent);


        return taotaoResult;
    }
}

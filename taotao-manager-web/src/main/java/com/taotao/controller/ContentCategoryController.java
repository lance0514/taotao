package com.taotao.controller;

import com.taotao.pojo.EasyUITreeNode;
import com.taotao.pojo.TaotaoResult;
import com.taotao.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;


    @RequestMapping("/list")
    @ResponseBody
    public List<EasyUITreeNode> getContentCategory(@RequestParam(value = "id", defaultValue = "0") long parentId) {


        List<EasyUITreeNode> categoryList = contentCategoryService.getContentCategoryList(parentId);


        return categoryList;
    }

    @RequestMapping("/create")
    @ResponseBody
    public TaotaoResult createContentCategory(Long parentId, String name) {


        TaotaoResult taotaoResult = contentCategoryService.createContentCategory(parentId, name);


        return taotaoResult;
    }
}

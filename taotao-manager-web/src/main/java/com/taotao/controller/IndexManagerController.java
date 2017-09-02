package com.taotao.controller;

import com.taotao.pojo.SearchItem;
import com.taotao.pojo.TaotaoResult;
import com.taotao.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexManagerController {

    @Autowired
    private SearchItemService searchItemService;

    @RequestMapping("/index/importall")
    @ResponseBody
    public TaotaoResult importAllItem() {
        TaotaoResult taotaoResult = null;
        try {
            taotaoResult = searchItemService.importAllItems();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return taotaoResult;
    }
}

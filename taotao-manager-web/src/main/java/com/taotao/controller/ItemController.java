package com.taotao.controller;

import com.taotao.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {

    @Autowired
    private ItemSerivce itemSerivce;

    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable Long itemId) {
        //根据商品Id查询商品信息
        TbItem itemById = itemSerivce.getItemById(itemId);

        return itemById;
    }


    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIDataGridResult<TbItem> getItemList(Integer page,Integer rows){
        EasyUIDataGridResult itemList = itemSerivce.getItemList(page, rows);
        return  itemList;
    }






}

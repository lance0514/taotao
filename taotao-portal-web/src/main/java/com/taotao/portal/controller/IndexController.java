package com.taotao.portal.controller;

import com.taotao.pojo.Ad1Node;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;
import com.taotao.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private ContentService contentService;

    @Value("${AD1_CD}")
    private Long AD1_CID;
    @Value("${AD1_HEIGHT}")
    private Integer AD1_HEIGHT;
    @Value("${AD1_WIDTH}")
    private Integer AD1_WIDTH;
    @Value("${AD1_HEIGHT_B}")
    private Integer AD1_HEIGHT_B;
    @Value("${AD1_WIDTH_B}")
    private Integer AD1_WIDTH_B;

    @RequestMapping("/index")
    public String showIndex(Model mode) {

        List<TbContent> contentList = contentService.getContentList(AD1_CID);


        List<Ad1Node> ad1Nodes = new ArrayList<>();

        Ad1Node ad1Node;

        for (TbContent tbContent :
                contentList) {

            ad1Node = new Ad1Node();

            ad1Node.setAlt(tbContent.getTitle());

            ad1Node.setHeight(String.valueOf(AD1_HEIGHT));

            ad1Node.setHeightB(String.valueOf(AD1_HEIGHT_B));

            ad1Node.setWidth(String.valueOf(AD1_WIDTH));

            ad1Node.setWidthB(String.valueOf(AD1_WIDTH_B));

            ad1Node.setHref(tbContent.getUrl());

            ad1Node.setSrc(tbContent.getPic());

            ad1Node.setSrcB(tbContent.getPic2());

            ad1Nodes.add(ad1Node);

        }


        mode.addAttribute("ad1", JsonUtils.objectToJson(ad1Nodes));
        return "index";
    }
}

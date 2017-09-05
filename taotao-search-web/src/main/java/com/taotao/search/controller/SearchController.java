package com.taotao.search.controller;

import com.taotao.pojo.SearchResult;
import com.taotao.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class SearchController {
    @Autowired
    private SearchService searchService;

    @Value("${ITEM_ROS}")
    private Integer ITEM_ROWS;

    public String search(@RequestParam("q") String queryString, @RequestParam(defaultValue = "1") Integer page, Model model) {

        try {
            SearchResult search = searchService.search(queryString, page, ITEM_ROWS);

            model.addAttribute("query",queryString);

            model.addAttribute("totalPages",search.getPageCount());

            model.addAttribute("itemList",search);

            model.addAttribute("page",page);
        } catch (Exception e) {
            e.printStackTrace();





        }

        return "search";
    }


}

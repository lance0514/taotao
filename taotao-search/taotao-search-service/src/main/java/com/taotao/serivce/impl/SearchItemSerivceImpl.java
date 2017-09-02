package com.taotao.serivce.impl;

import com.taotao.mapper.SearchItemMapper;
import com.taotao.pojo.SearchItem;
import com.taotao.pojo.TaotaoResult;
import com.taotao.service.SearchItemService;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchItemSerivceImpl implements SearchItemService {

    @Autowired
    private SearchItemMapper searchItemMapper;

    @Autowired
    private SolrServer solrServer;


    @Override
    public TaotaoResult importAllItems() throws Exception {


        List<SearchItem> itemList = searchItemMapper.getItemList();


        for (SearchItem searchItem :
                itemList) {

            SolrInputDocument document = new SolrInputDocument();

            document.addField("id", searchItem.getId());
            document.addField("item_title", searchItem.getTitle());
            document.addField("item_sell_point", searchItem.getSell_point());
            document.addField("item_price", searchItem.getPrice());
            document.addField("item_image", searchItem.getImage());
            document.addField("item_category_name", searchItem.getCategory_name());
            document.addField("item_desc", searchItem.getItem_desc());

            solrServer.add(document);
        }

        solrServer.commit();

        return TaotaoResult.ok();
    }
}

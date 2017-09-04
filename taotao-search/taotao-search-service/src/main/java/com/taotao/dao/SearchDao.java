package com.taotao.dao;

import com.taotao.pojo.SearchItem;
import com.taotao.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class SearchDao {

    @Autowired
    private SolrServer solrServer;

    public SearchResult search(SolrQuery solrQuery) throws SolrServerException {

        QueryResponse queryResponse = solrServer.query(solrQuery);

        SolrDocumentList solrDocuments = queryResponse.getResults();

        List<SearchItem> searchItems = new ArrayList<>();

        for (SolrDocument solrDocument :
                solrDocuments) {
            SearchItem searchItem = new SearchItem();
            searchItem.setCategory_name(solrDocument.get("item_category_name").toString());
            searchItem.setId(solrDocument.get("id").toString());
            searchItem.setImage(solrDocument.get("item_image").toString());
            searchItem.setPrice(Long.parseLong(solrDocument.get("item_price").toString()));
            searchItem.setSell_point(solrDocument.get("item_sell_point").toString());

            Map<String, Map<String, List<String>>> highLight = queryResponse.getHighlighting();


            List<String> strings = highLight.get(solrDocument.get("id")).get("item_title");

            String itemTitle = "";


            if (strings != null && strings.size() > 0) {
                itemTitle = strings.get(0);
            } else {
                itemTitle = solrDocument.get("item_title").toString();
            }

            searchItem.setTitle(itemTitle);

            searchItems.add(searchItem);
        }

        SearchResult searchResult = new SearchResult();


        searchResult.setItemList(searchItems);
        searchResult.setRecordCount(solrDocuments.getNumFound());

        return searchResult;
//        document.addField("id", searchItem.getId());
//        document.addField("item_title", searchItem.getTitle());
//        document.addField("item_sell_point", searchItem.getSell_point());
//        document.addField("item_price", searchItem.getPrice());
//        document.addField("item_image", searchItem.getImage());
//        document.addField("item_category_name", searchItem.getCategory_name());
//        document.addField("item_desc", searchItem.getItem_desc());

    }
}

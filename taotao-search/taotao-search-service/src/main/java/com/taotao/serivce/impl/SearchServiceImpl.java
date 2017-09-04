package com.taotao.serivce.impl;

import com.taotao.dao.SearchDao;
import com.taotao.pojo.SearchResult;
import com.taotao.service.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchDao searchDao;


    @Override
    public SearchResult search(String queryString, int page, int rows) throws Exception {


        SolrQuery solrQuery = new SolrQuery();


        solrQuery.setQuery(queryString);

        solrQuery.setStart((page - 1) * rows);

        solrQuery.setRows(rows);

        solrQuery.set("df", "item_title");

        solrQuery.setHighlight(true);

        solrQuery.addHighlightField("item_title");

        solrQuery.setHighlightSimplePre("<em style= \"color:red\">");

        solrQuery.setHighlightSimplePost("</em>");


        SearchResult search = searchDao.search(solrQuery);


        long recordCount = search.getRecordCount();

        long countPage = recordCount / rows;

        if (recordCount % rows > 0) {
            countPage++;
        }
        search.setPageCount(countPage);

        return search;
    }
}

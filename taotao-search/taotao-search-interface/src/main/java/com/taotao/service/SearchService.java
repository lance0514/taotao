package com.taotao.service;

import com.taotao.pojo.SearchResult;

public interface SearchService {
    SearchResult search(String queryString, int page, int rows) throws Exception;
}

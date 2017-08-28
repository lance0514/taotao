package com.taotao.service;

import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

import java.util.List;

public interface ContentService {
    TaotaoResult addContent(TbContent content);


    List<TbContent> getContentList(long cid);
}

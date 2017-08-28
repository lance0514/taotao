package com.taotao.service;

import com.taotao.pojo.EasyUITreeNode;
import com.taotao.pojo.TaotaoResult;

import java.util.List;

public interface ContentCategoryService {

    List<EasyUITreeNode> getContentCategoryList(long parentId);

    TaotaoResult createContentCategory(Long parentId, String name);
}

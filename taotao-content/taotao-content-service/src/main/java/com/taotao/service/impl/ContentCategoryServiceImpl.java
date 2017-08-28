package com.taotao.service.impl;

import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.EasyUITreeNode;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;

    @Override
    public List<EasyUITreeNode> getContentCategoryList(long parentId) {


        TbContentCategoryExample contentCategoryExample = new TbContentCategoryExample();

        TbContentCategoryExample.Criteria criteria = contentCategoryExample.createCriteria();

        criteria.andIdEqualTo(parentId);


        List<TbContentCategory> contentCategories = contentCategoryMapper.selectByExample(contentCategoryExample);

        List<EasyUITreeNode> nodes = new ArrayList<>();

        EasyUITreeNode easyUITreeNode;

        for (TbContentCategory tbContentCategory :
                contentCategories) {

            easyUITreeNode = new EasyUITreeNode();


            easyUITreeNode.setText(tbContentCategory.getName());
            easyUITreeNode.setState(tbContentCategory.getIsParent() ? "closed" : "open");
            easyUITreeNode.setId(tbContentCategory.getId());

            nodes.add(easyUITreeNode);
        }

        return nodes;
    }

    @Override
    public TaotaoResult createContentCategory(Long parentId, String name) {

        Date date = new Date();

        TbContentCategory tbContentCategory = new TbContentCategory();

        tbContentCategory.setCreated(date);
        tbContentCategory.setIsParent(false);
        tbContentCategory.setName(name);
        tbContentCategory.setSortOrder(1);
        tbContentCategory.setStatus(1);
        tbContentCategory.setUpdated(date);
        tbContentCategory.setParentId(parentId);


        contentCategoryMapper.insert(tbContentCategory);


        TbContentCategory parentNode = contentCategoryMapper.selectByPrimaryKey(parentId);


        if (!parentNode.getIsParent()) {

            parentNode.setIsParent(true);

            contentCategoryMapper.updateByPrimaryKey(parentNode);
        }


        return TaotaoResult.ok(tbContentCategory);
    }
}

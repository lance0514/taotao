package com.taotao.service.impl;

import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper tbContentMapper;


    @Override
    public TaotaoResult addContent(TbContent content) {

        Date date = new Date();

        content.setCreated(date);
        content.setUpdated(date);


        tbContentMapper.insert(content);

        return TaotaoResult.ok();
    }

    @Override
    public List<TbContent> getContentList(long cid) {


        TbContentExample tbContentExample = new TbContentExample();

        TbContentExample.Criteria criteria = tbContentExample.createCriteria();
        criteria.andIdEqualTo(cid);

        List<TbContent> tbContents = tbContentMapper.selectByExample(tbContentExample);

        return tbContents;
    }
}

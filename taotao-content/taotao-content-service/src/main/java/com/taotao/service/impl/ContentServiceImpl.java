package com.taotao.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.taotao.jedis.JedisClient;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.service.ContentService;
import com.taotao.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper tbContentMapper;

    @Autowired
    private JedisClient jedisClient;


    @Value("${CONTENT_KEY}")
    private String CONTENT_KEY;


    @Override
    public TaotaoResult addContent(TbContent content) {

        Date date = new Date();

        content.setCreated(date);
        content.setUpdated(date);


        tbContentMapper.insert(content);
        //删除redis缓存
        jedisClient.hdel(CONTENT_KEY, String.valueOf(content.getCategoryId()));

        return TaotaoResult.ok();
    }

    @Override
    public List<TbContent> getContentList(long cid) {


        String hget = jedisClient.hget(CONTENT_KEY, String.valueOf(cid));

        if (!StringUtils.isEmpty(hget)) {
            try {
                List<TbContent> tbContents = JsonUtils.jsonToList(hget, TbContent.class);
                return tbContents;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        TbContentExample tbContentExample = new TbContentExample();

        TbContentExample.Criteria criteria = tbContentExample.createCriteria();
        criteria.andCategoryIdEqualTo(cid);

        List<TbContent> tbContents = tbContentMapper.selectByExample(tbContentExample);

        String json = null;
        try {
            json = JsonUtils.objectToJson(tbContents);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        jedisClient.hset(CONTENT_KEY, String.valueOf(cid), json);
        return tbContents;
    }
}

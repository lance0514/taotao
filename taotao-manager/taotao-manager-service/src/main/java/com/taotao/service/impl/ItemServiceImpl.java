package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemSerivce {


    @Autowired
    private TbItemMapper tbItemMapper;

    @Override
    public TbItem getItemById(long itemId) {


        TbItem tbItem = tbItemMapper.selectByPrimaryKey(itemId);


        return tbItem;
    }

    @Override
    public EasyUIDataGridResult getItemList(int page, int rows) {


        PageHelper.startPage(page,rows);


        TbItemExample tbItemExample = new TbItemExample();

        List<TbItem> tbItems = tbItemMapper.selectByExample(tbItemExample);

        PageInfo<TbItem> pageIn = new PageInfo<TbItem>(tbItems);


        EasyUIDataGridResult<TbItem> easyUIDataGridResult = new EasyUIDataGridResult<>();

        easyUIDataGridResult.setList(tbItems);
        easyUIDataGridResult.setTotal(pageIn.getTotal());



        return easyUIDataGridResult;
    }


}

package com.taotao.pojo;

import java.io.Serializable;
import java.util.List;

public class EasyUIDataGridResult<T> implements Serializable {


    private Long total;

    private List<T> list;


    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}

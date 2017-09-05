package com.taotao.sso.service;

import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

public interface UserService {

    TaotaoResult checkData(String param, int type);

    TaotaoResult register(TbUser tbUser);

    TaotaoResult login(String username, String password);
}

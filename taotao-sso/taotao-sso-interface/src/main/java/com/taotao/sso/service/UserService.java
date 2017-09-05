package com.taotao.sso.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

import java.io.IOException;

public interface UserService {

    TaotaoResult checkData(String param, int type);

    TaotaoResult register(TbUser tbUser);

    TaotaoResult login(String username, String password) throws JsonProcessingException;



     TaotaoResult getUserByToken(String token) throws IOException;
}

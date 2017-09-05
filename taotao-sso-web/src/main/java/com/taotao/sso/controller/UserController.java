package com.taotao.sso.controller;

import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.UserService;
import com.taotao.util.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user/")
public class UserController {

    @Value("${TOKEN_KEY}")
    private String TOKEN_KEY;


    @Autowired
    private UserService userService;


    @RequestMapping("check/{param}/{type}")
    @ResponseBody
    public TaotaoResult checkData(@PathVariable String param, @PathVariable Integer type) {
        TaotaoResult taotaoResult = userService.checkData(param, type);
        return taotaoResult;
    }


    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult register(TbUser tbUser) {
        TaotaoResult taotaoResult = userService.register(tbUser);
        return taotaoResult;
    }


    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult login(String username, String password, HttpServletRequest request, HttpServletResponse response) {



        TaotaoResult taotaoResult = userService.login(username,password);


        String token = taotaoResult.getData().toString();


        CookieUtils.setCookie(request,response,TOKEN_KEY,token);




        return taotaoResult;
    }
}

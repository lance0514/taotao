package com.taotao.sso.service.impl;

import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.sso.jedis.JedisClient;
import com.taotao.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TbUserMapper tbUserMapper;


    @Autowired
    private JedisClient jedisClient;



    @Override
    public TaotaoResult checkData(String param, int type) {


        TbUserExample tbUserExample = new TbUserExample();

        TbUserExample.Criteria criteria = tbUserExample.createCriteria();


        //1、2、3分别代表username、phone、email
        if (type == 1) {
            criteria.andUsernameEqualTo(param);
        } else if (type == 2) {
            criteria.andPhoneEqualTo(param);
        } else if (type == 3) {
            criteria.andEmailEqualTo(param);
        } else {
            return TaotaoResult.build(400, "非法的参数");
        }


        List<TbUser> users = tbUserMapper.selectByExample(tbUserExample);
        if (users == null || users.size() == 0) {
            // 4、如果没有返回true。
            return TaotaoResult.ok(true);
        }
        // 5、使用TaotaoResult包装，并返回。
        return TaotaoResult.ok(false);

    }

    @Override
    public TaotaoResult register(TbUser user) {
        if (StringUtils.isBlank(user.getUsername())) {
            return TaotaoResult.build(400, "用户名不能为空");
        }
        if (StringUtils.isBlank(user.getPassword())) {
            return TaotaoResult.build(400, "密码不能为空");
        }
        //校验数据是否可用
        TaotaoResult result = checkData(user.getUsername(), 1);
        if (!(boolean) result.getData()) {
            return TaotaoResult.build(400, "此用户名已经被使用");
        }
        //校验电话是否可以
        if (StringUtils.isNotBlank(user.getPhone())) {
            result = checkData(user.getPhone(), 2);
            if (!(boolean) result.getData()) {
                return TaotaoResult.build(400, "此手机号已经被使用");
            }
        }
        //校验email是否可用
        if (StringUtils.isNotBlank(user.getEmail())) {
            result = checkData(user.getEmail(), 3);
            if (!(boolean) result.getData()) {
                return TaotaoResult.build(400, "此邮件地址已经被使用");
            }
        }
        // 2、补全TbUser其他属性。
        user.setCreated(new Date());
        user.setUpdated(new Date());
        // 3、密码要进行MD5加密。
        String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Pass);
        // 4、把用户信息插入到数据库中。
        tbUserMapper.insert(user);
        // 5、返回TaotaoResult。
        return TaotaoResult.ok();

    }

    @Override
    public TaotaoResult login(String username, String password) {


        TbUserExample tbUserExample = new TbUserExample();

        TbUserExample.Criteria criteria = tbUserExample.createCriteria();


        criteria.andUsernameEqualTo(username);


        List<TbUser> tbUsers = tbUserMapper.selectByExample(tbUserExample);


        if(tbUsers == null||tbUsers.size()==0){
            return TaotaoResult.build(400,"用户名和密码错误");
        }

        TbUser tbUser = tbUsers.get(0);


        if(!tbUser.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))){
            return TaotaoResult.build(400,"用户名和密码错误");
        }


        String token = UUID.randomUUID().toString();




        return null;
    }
}

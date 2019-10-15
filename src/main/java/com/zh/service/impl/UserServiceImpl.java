package com.zh.service.impl;

import com.zh.dao.impl.UserDaoImpl;

import com.zh.entity.User;

import com.zh.service.UserService;

import com.zh.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Hxx
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDaoImpl userDao;

    /**
     * 用户登录
     *
     * @param userId
     * @return
     */
    @Override
    public User findByUserId(String userId) {
        return userDao.findByUserId(userId);
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @Override
    public String addUser(User user) {
        return userDao.addUser(user);
    }

    /**
     * 修改用户密码
     *
     * @param user
     * @return
     */
    @Override
    public String updateUser(User user) {
        return userDao.updateUser(user);
    }

    /**
     * 生成token
     * @param userId
     * @return
     */
    @Override
    public String getToken(String userId) {

        //存入JWT的payload中生成token
        Map claims = new HashMap<String,Integer>();
        claims.put("user_userId",userId);
        String subject = "user";
        String token = null;
        try {
            //该token过期时间为12h
            token = JwtUtils.createJWT(claims, subject, 1000*60*60*12 );
        } catch (Exception e) {
            throw new RuntimeException("创建Token失败");
        }

        System.out.println("token:"+token);
        return token;
    }

}

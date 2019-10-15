package com.zh.service;

import com.zh.entity.User;
import org.springframework.stereotype.Component;


/**
 * @author Hxx
 */
@Component
public interface UserService {
    /**
     * 用户登录
     *
     * @param userId
     * @return
     */
    User findByUserId(String userId);

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    String addUser(User user);

    /**
     * 修改用户密码
     *
     * @param user
     * @return
     */
    String updateUser(User user);

    /**
     * 生成token
     *
     * @param userId
     * @return
     */
    String getToken(String userId);
}

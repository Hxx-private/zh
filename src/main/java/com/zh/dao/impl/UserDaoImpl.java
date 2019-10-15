package com.zh.dao.impl;

import com.zh.dao.UserDao;
import com.zh.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;


/**
 * @author Hxx
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 用户登录
     *
     * @param userId
     * @return
     */
    @Override
    public User findByUserId(String userId) {
        Query query = new Query();
        Criteria criteria = Criteria.where("userId").is(userId);
        query.addCriteria(criteria);
        User user = mongoTemplate.findOne(query, User.class);
        System.out.println("dao" + user);
        return user;
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @Override
    public String addUser(User user) {
        mongoTemplate.save(user);

        return "添加成功";
    }

    /**
     * 修改用户密码
     *
     * @param user
     * @return
     */
    @Override
    public String updateUser(User user) {
        Query query = new Query(Criteria.where("userId").is(user.getUserId()));
        System.out.println("changedao:" + user);
        Update update = new Update().set("UserPw", user.getUserPwNew());
        mongoTemplate.updateFirst(query, update, User.class);

        return "修改成功";
    }
}

package com.zh.controller;

import com.zh.entity.User;
import com.zh.service.impl.UserServiceImpl;
import com.zh.utils.AESUtils;
import com.zh.utils.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Hxx
 */
@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    private static final String KEY = "zhonghuan13xxxxx";
    Map<String, Object> result = new HashMap<>();

    /**
     * 用户登录
     *
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/user/login", produces = "application/json")
    public Map<String, Object> login(@RequestBody
                                             User user, HttpServletResponse response) throws Exception {
        //对用户名和密码进行校验
        if (userService.findByUserId(user.getUserId()) != null &&
                AESUtils.AESEncrypt(user.getUserPw(), KEY).
                        equals(userService.findByUserId(user.getUserId()).getUserPw())) {
            String token = userService.getToken(user.userId);
            response.setHeader("Set-Authorization-Token", token);
            response.setHeader("Access-Control-Expose-Headers", "Set-Authorization-Token");
            result.put("isSuccess", true);
            result.put("message", "登录成功");
            result.put("code", 200);
            result.put("data", user);
        }else {
            result.put("isSuccess", false);
            result.put("message", "登录失败");
            result.put("code", 500);
            return result;
        }
        return result;
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @PostMapping("/user/addUser")
    public AjaxResponse addUser(@RequestBody User user ,HttpServletResponse response) throws Exception {
        String token = userService.getToken(user.userId);
        response.setHeader("authorization", token);
        response.setHeader("Access-Control-Expose-Headers", "authorization");
        User user1 = new User();
        user1.setUserId(user.getUserId());

        String pwd = AESUtils.AESEncrypt(user.getUserPw(), KEY);
        user1.setUserPw(pwd);

        if (userService.findByUserId(user.getUserId()) != null) {
            System.out.println("用户名重复,请重新输入用户名！！");

            return AjaxResponse.fail();
        } else {
            userService.addUser(user1);
        }

        return AjaxResponse.success(user1);
    }

    /**
     * 修改密码
     *
     * @param user
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/user/changePassword", produces = "application/json")
    public Map<String, Object> updateUser(@RequestBody User user, HttpServletResponse response) throws Exception {
        String token = userService.getToken(user.userId);
        System.out.println("cs:" + user);
        //设置请求头
        response.setHeader("authorization", token);
        response.setHeader("Access-Control-Expose-Headers", "authorization");
        String newPwd = AESUtils.AESEncrypt(user.getUserPwNew(), KEY);
        //判断当前用户输入的旧密码是否与数据库中该用户的密码一致
        if (AESUtils.AESEncrypt(user.getUserPw(), KEY).equals(userService.findByUserId(user.getUserId()).getUserPw())) {
            user.setUserPwNew(newPwd);
            System.out.println("new:" + user);
            userService.updateUser(user);
            result.put("isSuccess", true);
            result.put("message", "修改成功");
            result.put("code", 200);
            result.put("data", user);
            return result;
        }
        result.put("isSuccess", false);
        result.put("message", "旧密码不正确，修改失败");
        result.put("code", 500);
        return result;
    }


}


package com.biao.xb.service;

import com.biao.xb.dao.UserDao;
import com.biao.xb.entity.User;

import java.util.Date;

public class UserService {
    private UserDao userDao = new UserDao();


    /**
     * 检查 邮箱 是否可用
     * @param email
     * @return
     */
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    /**
     * 检查 用户名 是否可用
     * @param username
     * @return
     */
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    /**
     * 用户注册
     * @param user
     */
    public void register(User user) {
        // 默认私密
        user.setIsSecret("0");
        // 真实姓名默认用户名
        user.setRealName(user.getUsername());
        // 注册时间为当前
        user.setRegisterTime(new Date());
        //默认头像
        user.setPic("http://localhost:8080/upload/def.png");

        userDao.save(user);
    }
}

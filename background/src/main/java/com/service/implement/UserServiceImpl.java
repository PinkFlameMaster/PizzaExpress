package com.service.implement;

import com.dao.UserDao;
import com.pojo.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public User checkLogin(String phoneNum, String password) {
        User user = userDao.findByPhoneNum(phoneNum);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public User loginByPhoneNum(String phoneNum) {
        return userDao.findByPhoneNum(phoneNum);
    }

    @Override
    public User loginByQQ(String qq) {
        return userDao.findByQQ(qq);
    }

    @Override
    public User loginByWechat(String wechat) {
        return userDao.findByWechat(wechat);
    }

    @Override
    public boolean modifyInfo(User user) {
        return userDao.modifyInfo(user);
    }

    @Override
    public boolean register(User user) {
        return userDao.registerNew(user);
    }

    @Override
    public boolean bindQQ(String phoneNum, String qq) {
        return userDao.addQQBinding(phoneNum, qq);
    }

    @Override
    public boolean bindWechat(String phoneNum, String wechat) {
        return userDao.addWechatBinding(phoneNum, wechat);
    }
}

package com.service.implement;

import com.dao.UserDao;
import com.pojo.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public User checkLogin(String phoneNum, String password) {
//        User user = userDao.findByUserPhoneNum(phoneNum);
//        if (user != null && user.getPassword().equals(password)) {
//            return user;
//        }
        return null;
    }

    @Override
    public User loginByPhoneNum(String phoneNum) {
        return null;
        //        return userDao.findByUserPhoneNum(phoneNum);
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
    public List<User> queryByNickname(String nickname) {
        return userDao.findByUserNickName(nickname);
    }

    @Override
    public List<User> queryByPhoneNum(String phoneNum) {
        return  userDao.findByUserPhoneNum(phoneNum);
    }

    @Override
    public List<User> queryByPhoneAndNickname(String nickname, String phoneNum) {
        Map paramMap=new HashMap();
        paramMap.put("nickname",nickname);
        paramMap.put("phoneNum",phoneNum);
        return userDao.findByUserPhoneNick(paramMap);
    }

    @Override
    public void deleteUser(String phoneNum) {
        userDao.deleteUserByPhoneNum(phoneNum);
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

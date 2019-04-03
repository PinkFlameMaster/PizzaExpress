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
}

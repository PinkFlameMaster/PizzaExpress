package com.dao;
import com.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    List<User> findByUserPhoneNum(String phoneNum);
    List<User> findByUserNickName(String nickName);
    List<User> findByUserPhoneNick(Map paramMap);
    void deleteUserByPhoneNum(String phoneNum);
}

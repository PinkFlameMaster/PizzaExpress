package com.service;
import com.pojo.User;

public interface UserService {
    User checkLogin(String phoneNum, String password);

    User loginByPhoneNum(String phoneNum);

    User loginByQQ(String qq);

    User loginByWechat(String wechat);

    boolean modifyInfo(User user);

    boolean register(User user);

    boolean bindQQ(String phoneNum, String qq);

    boolean bindWechat(String phoneNum, String wechat);
}

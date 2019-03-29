package com.service;
import com.pojo.User;

import java.util.List;

public interface UserService {
    User checkLogin(String phoneNum, String password);

    User loginByPhoneNum(String phoneNum);

    User loginByQQ(String qq);

    User loginByWechat(String wechat);

    List<User> queryByNickname(String nickname);

    List<User> queryByPhoneNum(String phoneNum);

    List<User> queryByPhoneAndNickname(String nickname, String phoneNum);

    void deleteUser(String phoneNum);

    boolean modifyInfo(User user);

    boolean register(User user);

    boolean bindQQ(String phoneNum, String qq);

    boolean bindWechat(String phoneNum, String wechat);
}

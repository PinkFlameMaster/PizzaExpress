package com.service;
import com.pojo.User;

import java.util.List;

public interface UserService {

    List<User> queryByNickname(String nickname);

    List<User> queryByPhoneNum(String phoneNum);

    List<User> queryByPhoneAndNickname(String nickname, String phoneNum);

    void deleteUser(String phoneNum);
}

package com.service;
import com.pojo.ReceiverAddress;
import com.pojo.User;

import java.util.List;

public interface UserService {
    User checkLogin(String phoneNum, String password);

    String generateIDCode(String phoneNum);

    String sendIDCode(String phoneNum) throws Exception;

    User loginByPhoneNum(String phoneNum);

    User loginByQQ(String qq);

    User loginByWechat(String wechat);

    List<User> queryByNickname(String nickname);

    List<User> queryByPhoneNum(String phoneNum);

    List<User> queryByPhoneAndNickname(String nickname, String phoneNum);

    User findByUserPhonePwd(String phoneNum,String userPwd);

    User findByUserPhone(String phoneNum);

    boolean modifyInfo(User user);

    boolean register(User user);

    boolean bindQQ(String phoneNum, String qq);

    boolean bindWechat(String phoneNum, String wechat);

    int addAddress(ReceiverAddress receiverAddress);

    List<ReceiverAddress> getAddressByPhoneNum(String phoneNum);

    ReceiverAddress getAddressById(int id);

    void modifyAddress(ReceiverAddress receiverAddress);

    void deleteAddress(int id);
}

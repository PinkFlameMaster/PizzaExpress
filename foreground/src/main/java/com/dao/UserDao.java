package com.dao;
import com.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    /**
     * 通过号码查询用户信息
     * @param phoneNum 用户号码
     * @return 若已经注册，则返回用户信息，否则返回null
     */
    List<User> findByUserPhoneNum(String phoneNum);
    List<User> findByUserNickName(String nickName);
    List<User> findByUserPhoneNick(Map paramMap);

    /**
     * 通过QQ查询用户信息
     * @param qq 用户QQ号
     * @return 若已绑定QQ号，则返回用户信息，否则返回null
     */
    User findByQQ(String qq);

    /**
     * 通过微信号查询用户信息
     * @param wechat 用户微信号
     * @return 若已绑定微信，则返回用户信息，否则返回null
     */
    User findByWechat(String wechat);

    /**
     * 修改用户信息
     * @param user 修改后的用户信息
     * @return 是否修改成功
     */
    boolean modifyInfo(User user);

    /**
     * 注册新用户
     * @param user 新注册的用户信息
     * @return 是否注册成功
     **/
    boolean registerNew(User user);

    /**
     * 增加用户与QQ号的绑定信息
     * @param phoneNum 需要绑定的用户号码
     * @param qq 绑定的QQ号
     * @return 是否绑定成功
     */
    boolean addQQBinding(String phoneNum, String qq);

    /**
     * 增加用户与微信号的绑定信息
     * @param phoneNum 需要绑定的用户号码
     * @param wechat 绑定的微信号
     * @return 是否绑定成功
     */
    boolean addWechatBinding(String phoneNum, String wechat);
}

package com.dao;

import com.pojo.ReceiverAddress;

import java.util.List;

public interface ReceiverAddressDao {
    /**
     * 通过收货地址的ID来得到收货地址信息
     * @param id 收货地址的ID
     * @return 找到的收货地址信息，未找到则为null
     */
    ReceiverAddress findById(int id);

    /**
     * 查找用户已存的收货地址
     * @param userPhoneNum 用户的号码
     * @return 用户已存的收货地址，查询失败返回空集合
     */
    List<ReceiverAddress> findByUser(String userPhoneNum);

    /**
     * 新增收货地址
     * @param address 新增的收货地址
     * @return 新增收货地址的id，-1表示失败
     */
    int addNew(ReceiverAddress address);

    /**
     * 删除某条收货地址
     * @param id 被删除的id
     */
    void delete(int id);

    /**
     * 修改某条收货地址的信息
     * @param address 修改后的收货地址
     */
    void modify(ReceiverAddress address);
}

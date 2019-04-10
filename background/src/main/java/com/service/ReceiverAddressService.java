package com.service;

import com.pojo.ReceiverAddress;

import java.util.List;

public interface ReceiverAddressService {
    List<ReceiverAddress> receiverAddressListByPhoneNum(String phoneNum);
}

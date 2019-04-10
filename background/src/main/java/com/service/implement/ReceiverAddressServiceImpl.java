package com.service.implement;

import com.dao.ReceiverAddressDao;
import com.pojo.ReceiverAddress;
import com.service.ReceiverAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiverAddressServiceImpl implements ReceiverAddressService {
    @Autowired
    ReceiverAddressDao receiverAddressDao;
    @Override
    public List<ReceiverAddress> receiverAddressListByPhoneNum(String phoneNum) {
        return receiverAddressDao.findByUser(phoneNum);
    }
}

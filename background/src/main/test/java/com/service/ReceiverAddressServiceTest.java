package com.service;

import baseUnitTest.BaseUnitTest;
import com.pojo.ReceiverAddress;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ReceiverAddressServiceTest extends BaseUnitTest {
    @Autowired
    private ReceiverAddressService receiverAddressService;

    @Test
    public void testReceiverAddressListByPhoneNum()
    {
        String phoneNum = "001";
        for (ReceiverAddress address : receiverAddressService.receiverAddressListByPhoneNum(phoneNum))
            Assert.assertEquals(phoneNum, address.getUserPhoneNum());
    }
}

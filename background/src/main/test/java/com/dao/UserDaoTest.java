package com.dao;

import baseUnitTest.BaseUnitTest;
import com.pojo.User;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoTest extends BaseUnitTest {
    @Resource
    private UserDao userDao;

    @Test
    public void testFindByUserNickName()
    {
        String nickName = "nickname1";
        List<User> users = userDao.findByUserNickName(nickName);
        for (User user : users)
            Assert.assertEquals(nickName, user.getNickname());
    }

    @Test
    public void testFindByUserPhoneNum()
    {
        String phoneNum = "001";
        List<User> users = userDao.findByUserPhoneNum(phoneNum);
        for (User user : users)
            Assert.assertEquals(phoneNum, user.getPhoneNum());
    }

    @Test
    public void testFindByUserPhoneNick()
    {
        String phoneNum = "001";
        String nickname = "nickname1";
        Map input = new HashMap();
        input.put("phoneNum", phoneNum);
        input.put("nickname", nickname);
        List<User> users = userDao.findByUserPhoneNick(input);
        for (User user : users)
        {
            Assert.assertEquals(phoneNum, user.getPhoneNum());
            Assert.assertEquals(nickname, user.getNickname());
        }
    }

    @Test
    public void testDeleteUserByPhoneNum()
    {
        String phoneNum = "000";
        userDao.deleteUserByPhoneNum(phoneNum);
        List<User> users = userDao.findByUserPhoneNum(phoneNum);
        for (User user : users)
            Assert.assertEquals(true, user.isDeleted());
    }
}

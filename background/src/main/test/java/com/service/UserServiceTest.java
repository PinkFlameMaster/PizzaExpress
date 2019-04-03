package com.service;

import baseUnitTest.BaseUnitTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.pojo.User;
import java.util.List;

public class UserServiceTest extends BaseUnitTest {
    @Autowired
    private UserService userService;

    @Test
    public void testQueryByPhoneNum()
    {
        String phoneNum = "001";
        List<User> users = userService.queryByPhoneNum(phoneNum);
        for (User user : users)
            Assert.assertEquals(phoneNum, user.getPhoneNum());
    }

    @Test
    public void testQueryByNickname()
    {
        String nickname = "nickname1";
        List<User> users = userService.queryByNickname(nickname);
        for (User user : users)
            Assert.assertEquals(nickname, user.getNickname());
    }

    @Test
    public void testQueryByPhoneAndNickname()
    {
        String nickname = "nickname1";
        String phoneNum = "001";
        List<User> users = userService.queryByPhoneAndNickname(nickname, phoneNum);
        for (User user : users) {
            Assert.assertEquals(nickname, user.getNickname());
            Assert.assertEquals(phoneNum, user.getPhoneNum());
        }
    }

    @Test
    public void testDeleteUser()
    {
        String phoneNum = "000";
        userService.deleteUser(phoneNum);
        List<User> users = userService.queryByPhoneNum(phoneNum);
        for (User user : users)
            Assert.assertEquals(true, user.isDeleted());
    }
}

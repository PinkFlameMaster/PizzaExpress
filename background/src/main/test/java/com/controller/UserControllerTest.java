package com.controller;

import baseUnitTest.BaseUnitTest;
import com.vo.ReturnMsg;
import net.sf.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.pojo.User;

import java.util.List;

public class UserControllerTest extends BaseUnitTest {
    @Autowired
    private UserController controller;

    @Test
    public void testSearchUsersByPhone()
    {
        String phoneNum = "001";

        User user = new User();
        user.setPhoneNum(phoneNum);

        JSONObject json = JSONObject.fromObject(user);
        ReturnMsg ret = controller.searchUsers(null, json.toString(), "正常");
        Assert.assertEquals("success", ret.getStatus());
        for (User result : (List<User>)ret.getData())
            Assert.assertEquals(phoneNum, result.getPhoneNum());
    }

    @Test
    public void testSearchUsersByNickname()
    {
        String nickName = "nickname1";

        User user = new User();
        user.setNickname(nickName);

        JSONObject json = JSONObject.fromObject(user);
        ReturnMsg ret = controller.searchUsers(null, json.toString(), "正常");
        Assert.assertEquals("success", ret.getStatus());
        for (User result : (List<User>)ret.getData())
            Assert.assertEquals(nickName, result.getNickname());
    }

    @Test
    public void testSearchUsersByPhoneAndNickname()
    {
        String phoneNum = "001";
        String nickName = "nickname1";

        User user = new User();
        user.setPhoneNum(phoneNum);
        user.setNickname(nickName);

        JSONObject json = JSONObject.fromObject(user);
        ReturnMsg ret = controller.searchUsers(null, json.toString(), "已删除");
        Assert.assertEquals("success", ret.getStatus());
        for (User result : (List<User>)ret.getData()) {
            Assert.assertEquals(phoneNum, result.getPhoneNum());
            Assert.assertEquals(nickName, result.getNickname());
        }
    }

    @Test
    public void testDeleteByPhoneNum()
    {
        String[] phoneNums = new String[]{"001"};
        ReturnMsg ret = controller.deleteByPhoneNum(null, phoneNums);
        Assert.assertEquals("success", ret.getStatus());
    }
}

package com.service.implement;

import com.dao.UserDao;
import com.pojo.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public User checkLogin(String phoneNum, String password) {
//        User user = userDao.findByUserPhoneNum(phoneNum);
//        if (user != null && user.getPassword().equals(password)) {
//            return user;
//        }
        return null;
    }

    @Override
    public String generateIDCode(String phoneNum)
    {
        String code="";
        if(phoneNum==null || phoneNum=="")
            return code;

        code=smsCode();
        DefaultProfile profile = DefaultProfile.getProfile("default", "<accessKeyId>", "<accessSecret>");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phoneNum);
        request.putQueryParameter("SignName", "PizzaExpress");
        request.putQueryParameter("TemplateCode", "SMS_161360041");
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            code="error";
            e.printStackTrace();
        } catch (ClientException e) {
            code="error";
            e.printStackTrace();
        }
        finally
        {
            return code;
        }
    }

    // 创建验证码
    public static String smsCode() {
        String random = (int) ((Math.random() * 9 + 1) * 100000) + "";
        return random;
    }

    @Override
    public User loginByPhoneNum(String phoneNum) {
        return null;
        //        return userDao.findByUserPhoneNum(phoneNum);
    }

    @Override
    public User loginByQQ(String qq) {
        return userDao.findByQQ(qq);
    }

    @Override
    public User loginByWechat(String wechat) {
        return userDao.findByWechat(wechat);
    }

    @Override
    public List<User> queryByNickname(String nickname) {
        return userDao.findByUserNickName(nickname);
    }

    @Override
    public List<User> queryByPhoneNum(String phoneNum) {
        return  userDao.findByUserPhoneNum(phoneNum);
    }

    @Override
    public List<User> queryByPhoneAndNickname(String nickname, String phoneNum) {
        Map paramMap=new HashMap();
        paramMap.put("nickname",nickname);
        paramMap.put("phoneNum",phoneNum);
        return userDao.findByUserPhoneNick(paramMap);
    }


    @Override
    public boolean modifyInfo(User user) {
        return userDao.modifyInfo(user);
    }

    @Override
    public boolean register(User user) {
        return userDao.registerNew(user);
    }

    @Override
    public boolean bindQQ(String phoneNum, String qq) {
        return userDao.addQQBinding(phoneNum, qq);
    }

    @Override
    public boolean bindWechat(String phoneNum, String wechat) {
        return userDao.addWechatBinding(phoneNum, wechat);
    }
}

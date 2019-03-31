package com.service.implement;

import com.dao.ReceiverAddressDao;
import com.dao.UserDao;
import com.pojo.ReceiverAddress;
import com.pojo.User;
import com.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
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
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    ReceiverAddressDao receiverAddressDao;

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
//        if(phoneNum==null || phoneNum=="")
//            return code;
//
//        code=smsCode();
//        DefaultProfile profile = DefaultProfile.getProfile("default", "LTAIkFiQm30BvsIU", "tXco3J10pjGUMVtEsrBW3V5mQXZetj");
//        IAcsClient client = new DefaultAcsClient(profile);
//
//        CommonRequest request = new CommonRequest();
//        //request.setProtocol(ProtocolType.HTTPS);
//        request.setMethod(MethodType.POST);
//        request.setDomain("dysmsapi.aliyuncs.com");
//        request.setVersion("2017-05-25");
//        request.setAction("SendSms");
//        request.putQueryParameter("PhoneNumbers", phoneNum);
//        request.putQueryParameter("SignName", "PizzaExpress");
//        request.putQueryParameter("TemplateCode", "SMS_161360041");
//        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
//        try {
//            CommonResponse response = client.getCommonResponse(request);
//        } catch (ServerException e) {
//            code="error";
//            e.printStackTrace();
//        } catch (ClientException e) {
//            code="error";
//            e.printStackTrace();
//        }
//        finally
//        {
//            return code;
//        }
        return code;
    }

    @Override
    public String sendIDCode(String phoneNum) throws Exception {
        String code="";
        code=smsCode();
        final URL url=new URL(generateSign(code));
        Thread connectToServe = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        HttpURLConnection urlConnection;
                        try {
                            urlConnection = (HttpURLConnection) url.openConnection();//打开http连接
                            urlConnection.setConnectTimeout(5000);//连接的超时时间
                            urlConnection.setUseCaches(false);//不使用缓存
                            urlConnection.setInstanceFollowRedirects(true);//是成员函数，仅作用于当前函数,设置这个连接是否可以被重定向
                            urlConnection.setReadTimeout(5000);//响应的超时时间
                            urlConnection.setDoInput(true);//设置这个连接是否可以写入数据
                            urlConnection.setDoOutput(true);//设置这个连接是否可以输出数据
                            urlConnection.setRequestMethod("GET");//设置请求的方式
                            urlConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");//设置消息的类型
                            urlConnection.connect();// 连接，从上述至此的配置必须要在connect之前完成，实际上它只是建立了一个与服务器的TCP连接

                            //得到响应码
                            int responseCode = urlConnection.getResponseCode();
                            if (responseCode == HttpURLConnection.HTTP_OK) {
                                //得到响应流
                                System.out.println(responseCode);
                                InputStream inputStream = urlConnection.getInputStream();
                                //获取响应
                                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                                String line;
                                StringBuffer s = new StringBuffer();
                                int i=0;
                                while ((line = reader.readLine()) != null) {
                                    s.append(line);
                                }
                                System.out.println(s);
                                reader.close();
                                urlConnection.disconnect();
                            }
                        } catch (ProtocolException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
        connectToServe.start();
        try
        {
            connectToServe.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return code;
    }

    public String generateSign(String code) throws Exception
    {
        String accessKeyId = "LTAIkFiQm30BvsIU";
        String accessSecret = "tXco3J10pjGUMVtEsrBW3V5mQXZetj";
        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(new java.util.SimpleTimeZone(0, "GMT"));// 这里一定要设置GMT时区
        java.util.Map<String, String> paras = new java.util.HashMap<String, String>();
        // 1. 系统参数
        paras.put("SignatureMethod", "HMAC-SHA1");
        paras.put("SignatureNonce", java.util.UUID.randomUUID().toString());
        paras.put("AccessKeyId", accessKeyId);
        paras.put("SignatureVersion", "1.0");
        paras.put("Timestamp", df.format(new java.util.Date()));
        paras.put("Format", "json");
        // 2. 业务API参数
        paras.put("Action", "SendSms");
        paras.put("Version", "2017-05-25");
        //paras.put("RegionId", "cn-hangzhou");
        paras.put("PhoneNumbers", "17702103242");
        paras.put("SignName", "PizzaExpress");
        paras.put("TemplateParam", "{\"code\":\""+code+"\"}");
        paras.put("TemplateCode", "SMS_161360041");
        paras.put("OutId", "123");
        // 3. 去除签名关键字Key
        if (paras.containsKey("Signature"))
            paras.remove("Signature");
        // 4. 参数KEY排序
        java.util.TreeMap<String, String> sortParas = new java.util.TreeMap<String, String>();
        sortParas.putAll(paras);
        // 5. 构造待签名的字符串
        java.util.Iterator<String> it = sortParas.keySet().iterator();
        StringBuilder sortQueryStringTmp = new StringBuilder();
        while (it.hasNext()) {
            String key = it.next();
            sortQueryStringTmp.append("&").append(specialUrlEncode(key)).append("=").append(specialUrlEncode(paras.get(key)));
        }
        String sortedQueryString = sortQueryStringTmp.substring(1);// 去除第一个多余的&符号
        StringBuilder stringToSign = new StringBuilder();
        stringToSign.append("GET").append("&");
        stringToSign.append(specialUrlEncode("/")).append("&");
        stringToSign.append(specialUrlEncode(sortedQueryString));
        String sign = sign(accessSecret + "&", stringToSign.toString());
        // 6. 签名最后也要做特殊URL编码
        String signature = specialUrlEncode(sign);
//        System.out.println(paras.get("SignatureNonce"));
//        System.out.println("\r\n=========\r\n");
//        System.out.println(paras.get("Timestamp"));
//        System.out.println("\r\n=========\r\n");
//        System.out.println(sortedQueryString);
//        System.out.println("\r\n=========\r\n");
//        System.out.println(stringToSign.toString());
//        System.out.println("\r\n=========\r\n");
//        System.out.println(sign);
//        System.out.println("\r\n=========\r\n");
//        System.out.println(signature);
//        System.out.println("\r\n=========\r\n");
        // 最终打印出合法GET请求的URL
        System.out.println("http://dysmsapi.aliyuncs.com/?Signature=" + signature + sortQueryStringTmp);
        return "http://dysmsapi.aliyuncs.com/?Signature=" + signature + sortQueryStringTmp;
    }
    public static String specialUrlEncode(String value) throws Exception {
        return java.net.URLEncoder.encode(value, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
    }
    public String sign(String accessSecret, String stringToSign) throws Exception {
        javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA1");
        mac.init(new javax.crypto.spec.SecretKeySpec(accessSecret.getBytes("UTF-8"), "HmacSHA1"));
        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
        return new sun.misc.BASE64Encoder().encode(signData);
    }

    // 创建验证码
    public  String smsCode() {
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
    public User findByUserPhonePwd(String phoneNum, String userPwd) {
        Map paramMap=new HashMap();
        paramMap.put("password",userPwd);
        paramMap.put("phoneNum",phoneNum);
        return userDao.findByUserPhonePwd(paramMap);
    }

    @Override
    public User findByUserPhone(String phoneNum) {
        return userDao.findByUserPhone(phoneNum);
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

    @Override
    public int addAddress(ReceiverAddress receiverAddress) {
        return receiverAddressDao.addNew(receiverAddress);
    }

    @Override
    public List<ReceiverAddress> getAddressByPhoneNum(String phoneNum) {
        return receiverAddressDao.findByUser(phoneNum);
    }

    @Override
    public ReceiverAddress getAddressById(int id) {
        return receiverAddressDao.findById(id);
    }

    @Override
    public void modifyAddress(ReceiverAddress receiverAddress) {
        receiverAddressDao.modify(receiverAddress);
    }

    @Override
    public void deleteAddress(int id) {
        receiverAddressDao.delete(id);
    }
}

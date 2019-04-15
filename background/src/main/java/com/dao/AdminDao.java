package com.dao;
import com.pojo.Admin;
public interface AdminDao {
        /**
         * 查找用户名和密码
         * @param username 登录用户名
         * @return
         */
        Admin findByUsername(String username);
        Admin findByFactory(int factoryId);
}

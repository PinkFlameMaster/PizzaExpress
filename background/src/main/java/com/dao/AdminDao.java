package com.dao;
import com.pojo.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminDao {
        /**
         * 查找用户名和密码
         * @param username 登录用户名
         * @return
         */
        Admin findByUsername(String username);
        Admin findByFactory(int factoryId);
        List<Admin > getAllAdmin();
        void modifyByUsername(@Param("role")String role, @Param("username")String username);
}

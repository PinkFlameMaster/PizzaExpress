package com.service;
import com.pojo.Admin;

import java.util.List;


public interface AdminService {
    Admin checkLogin(String username,String password);
    List<Admin> getAllAdmin();
    void modifyRole(String role, String username);
}

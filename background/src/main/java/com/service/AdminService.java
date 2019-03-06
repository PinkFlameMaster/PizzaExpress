package com.service;
import com.pojo.Admin;


public interface AdminService {
    Admin checkLogin(String username,String password);
}

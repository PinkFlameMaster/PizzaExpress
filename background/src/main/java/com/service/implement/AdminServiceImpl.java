package com.service.implement;

import com.dao.AdminDao;
import com.pojo.Admin;
import com.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDao adminDao;


    @Override
    public Admin checkLogin(String username, String password) {
        Admin user = adminDao.findByUsername(username);
        if(user != null && user.getPassword().equals(password)){
            return user;
        }
        return null;
    }

    @Override
    public List<Admin> getAllAdmin() {
        return adminDao.getAllAdmin();
    }

    @Override
    public void modifyRole(String role, String username) {
        adminDao.modifyByUsername(role,username);
    }
}

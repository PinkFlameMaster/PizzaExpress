package com.service.implement;

import com.dao.MenuItemDao;
import com.pojo.MenuItem;
import com.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuItemDao menuItemDao;

    @Override
    public List<MenuItem> queryAllOnSale() {
        return menuItemDao.findAllOnSale();
    }

    @Override
    public MenuItem queryItemByID(int id) {
        return menuItemDao.findById(id);
    }
}

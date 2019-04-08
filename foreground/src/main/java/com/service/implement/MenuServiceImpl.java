package com.service.implement;

import com.dao.MenuItemDao;
import com.pojo.MenuItem;
import com.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<MenuItem> queryAllOnSaleByPriceAndOrderFromLow(float minPrice,float maxPrice) {
        Map map=new HashMap();
        map.put("minPrice",minPrice);
        map.put("maxPrice",maxPrice);
        return menuItemDao.queryAllOnSaleByPriceAndOrderFromLow(map);
    }

    @Override
    public List<MenuItem> queryAllOnSaleByPriceAndOrderFromHigh(float minPrice,float maxPrice) {
        Map map=new HashMap();
        map.put("minPrice",minPrice);
        map.put("maxPrice",maxPrice);
        return menuItemDao.queryAllOnSaleByPriceAndOrderFromHigh(map);
    }

    @Override
    public List<MenuItem> queryAllOnSaleByInfo(String info) {
        return menuItemDao.queryAllOnSaleByInfo(info);
    }
}

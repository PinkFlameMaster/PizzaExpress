package com.service;

import com.pojo.MenuItem;

import java.util.List;

public interface MenuService {

    List<MenuItem> queryAllOnSale();

    MenuItem queryItemByID(int id);

}

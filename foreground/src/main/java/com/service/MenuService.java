package com.service;

import com.pojo.MenuItem;

import java.util.List;

public interface MenuService {

    List<MenuItem> queryAllOnSale();

    MenuItem queryItemByID(int id);

    List<MenuItem> queryAllOnSaleByPriceAndOrderFromLow(float minPrice,float maxPrice);

    List<MenuItem> queryAllOnSaleByPriceAndOrderFromHigh(float minPrice,float maxPrice);

    List<MenuItem> queryAllOnSaleByInfo(String info);

}

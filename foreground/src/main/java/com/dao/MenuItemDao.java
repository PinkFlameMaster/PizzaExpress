package com.dao;

import com.pojo.MenuItem;

import java.util.List;
import java.util.Map;

public interface MenuItemDao {
    /**
     * 通过ID查找菜单条目
     * @param id 查找的ID
     * @return 找到的菜单条目，若没找到，返回null
     */
    MenuItem findById(int id);

    /**
     * 获得所有在售菜品
     * @return 所有在售的菜品集合，若没找到，返回空集合
     */
    List<MenuItem> findAllOnSale();

    /**
     * 获得所有在售菜品,从低到高排序，同时根据价格区间查询
     * @return 所有在售的菜品集合，若没找到，返回空集合
     */
    List<MenuItem> queryAllOnSaleByPriceAndOrderFromLow(Map map);

    /**
     * 获得所有在售菜品,从高到低排序，同时根据价格区间查询
     * @return 所有在售的菜品集合，若没找到，返回空集合
     */
    List<MenuItem> queryAllOnSaleByPriceAndOrderFromHigh(Map map);


    /**
     * 获得所有在售菜品,通过info进行模糊查询
     * @return 所有在售的菜品集合，若没找到，返回空集合
     */
    List<MenuItem> queryAllOnSaleByInfo(String info);

    /**
     * 新增菜单条目
     * @param menuItem 新增的菜单条目
     * @return 新增的id，新增失败返回-1
     */
    int addNew(MenuItem menuItem);

    /**
     * 删除菜单条目
     * @param id 被删的菜单条目id
     * @return 删除是否成功
     */
    boolean delete(int id);

    /**
     * 修改菜单条目信息
     * @param menuItem 修改后的菜单条目信息
     * @return 是否修改成功
     */
    boolean modify(MenuItem menuItem);
}

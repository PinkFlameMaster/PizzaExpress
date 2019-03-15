package com.dao;

import com.pojo.MenuItem;

import java.util.List;

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

package com.dao;

import com.vo.OrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDao {
    List<OrderVo> findOrderComplex(@Param("name")String name, @Param("phone")String phone, @Param("factoryName")String factoryName);
}

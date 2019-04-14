package com.dao;

import com.pojo.Factory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FactoryDao {
    Factory findFactoryById(int id);
    List<Factory> findFactoryByName(String name);
    int count();
    void createFactory(int id);
    void updateFactory(@Param("id")int id, @Param("name")String name,@Param("address")String address,
                        @Param("latitude")float latitude,@Param("longitude")float longitude,
                        @Param("phoneNum")String phoneNum, @Param("businessTimeFrom")String businessTimeFrom,@Param("businessTimeTo")String businessTimeTo
    );
}

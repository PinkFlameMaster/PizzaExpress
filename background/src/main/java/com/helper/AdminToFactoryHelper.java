package com.helper;

import com.pojo.Admin;
import com.pojo.Factory;
import com.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;


public class AdminToFactoryHelper {

    FactoryService factoryService = SpringTool.getBean(FactoryService.class);
    public int id;
    public AdminToFactoryHelper(Admin admin){

        id = admin.getFactoryId();
    }
    public String getFactoryName (){
        return factoryService.getAdminFactory(id).getName();
    }
}

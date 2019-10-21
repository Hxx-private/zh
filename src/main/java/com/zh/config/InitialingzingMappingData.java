package com.zh.config;


import com.zh.entity.DataModel;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * 从Excel文件中初始化数据进静态对象，方便以后读取。避免重复读取。
 * @author Hxx
 */
@Component
public class InitialingzingMappingData implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {

        System.out.println("InitializingMappingData");
        DataModel.InitCellMapping();

    }
}
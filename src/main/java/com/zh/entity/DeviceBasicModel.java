package com.zh.entity;

import lombok.Data;
import org.bson.types.ObjectId;

/**
 * @author Hxx
 */
@Data
public class DeviceBasicModel
    {        
  
        public ObjectId _id ;
      
        public String eqpNo ;//设备编号
      
        public String  eqpParam ;//设备参数
      
        public String  paramVal ;//值
      
        public String  ValType ;//数据类型
      
        public long  time ;//时间
      
        public String  unit ;//单位
    }
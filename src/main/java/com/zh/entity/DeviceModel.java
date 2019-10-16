package com.zh.entity;

import lombok.Data;

/**
 * @author Hxx
 */
@Data
public class DeviceModel extends DeviceMapping
    {
        public TagModel[] paramList ;
        public String errorState ;
        public String alarmState ;
    }
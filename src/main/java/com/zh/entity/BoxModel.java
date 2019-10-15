package com.zh.entity;

import lombok.Data;

import java.util.Map;
@Data
public class BoxModel extends CellModel
    {
        public String boxId ;

        public String boxName ;

        public int index ;

        public double thick ;

        public Map<String,String> boxInfo ;

    }
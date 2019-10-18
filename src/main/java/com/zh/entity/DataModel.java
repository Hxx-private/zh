package com.zh.entity;

import com.zh.readexcel.readExcel;

import java.util.Map;

/**
 * @author Hxx
 */
public class DataModel {

    public static void InitCellMapping() throws Exception {
        Map<Integer, CellMappingModel> map = new readExcel().CellMappingread("CellMapping.xlsx");
        DataEntity.dicCellMapping=map;
    }
}

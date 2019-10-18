package com.zh.entity;

import com.zh.readexcel.readExcel;

import java.util.Map;

import static com.zh.service.impl.DataService.dicCellMapping;

/**
 * @author Hxx
 */
public class DataModel {

    public static void InitCellMapping() throws Exception {
        Map<Integer, CellMappingModel> map = new readExcel().CellMappingread("CellMapping.xlsx");
        dicCellMapping=map;
    }
}

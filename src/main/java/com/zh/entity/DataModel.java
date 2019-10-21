package com.zh.entity;

import com.zh.utils.ExcelUtilsBySelf;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.HashMap;

/**
 * @author Hxx
 */

public class DataModel {
    public static void main(String[] args) throws Exception {
        DataModel dataModel = new DataModel();
        dataModel.InitCellMapping();
    }
    public static Boolean InitCellMapping() throws Exception {
        ExcelUtilsBySelf excelUtils = new ExcelUtilsBySelf();
        HashMap<Integer, CellModel> map = new HashMap<Integer,CellModel>();
        ReturnValue returnValue = new ReturnValue();
        //获取xlsx文件路径
        File FileUrl = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "static/CellMapping.xlsx");
//        excelUtils.ExcelToEntityList(FileUrl.toString(),map,returnValue);

        return false;
    }
}

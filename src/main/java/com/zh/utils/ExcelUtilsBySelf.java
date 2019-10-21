package com.zh.utils;

import com.zh.entity.ReturnValue;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class ExcelUtilsBySelf<T> {
    public   Map<String,String> Dictionary = new HashMap<>();

    public boolean ExcelToEntityList(String filePathStr, HashMap<Integer, T> dicEntity, ReturnValue returnValue, int maxErrLine, boolean enableCustomCols) throws Exception {
        maxErrLine=100;
        enableCustomCols=false;
        ReturnValue retValue = new ReturnValue();
        //打开Excel文件
        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(ResourceUtils.getFile(filePathStr.toString())));
        //打开第一张工作表
        if(null==wb){
          retValue.setErroMsg("错误: 文件无法读取, 无法识别文件类型.");
            dicEntity=null;
          return false;
        }
        XSSFSheet sheet = wb.getSheetAt(0);
        if(null==sheet){
            retValue.setErroMsg("错误: 文件无法读取, 因为没有有效的工作表.");
            dicEntity=null;
            return false;
        }
        //表中第三行数据
        XSSFRow keyRow = sheet.getRow(2);
        //表中第二行数据
        XSSFRow nameRow = sheet.getRow(1);
        int importFieldCount = keyRow.getLastCellNum();
        int importFieldNameCount = nameRow.getLastCellNum();
        if (null == keyRow || importFieldCount == 0)
        {
            returnValue.setErroMsg("错误: 文件无法读取, 因为没有有效的数据.");
            dicEntity = null;
            return false;
        }
        /**
         *
         */
        //确定要不要导入额外的字段
        boolean importCustomFields = false;
        Class customFieldsPropertyInfo = null;
        Map<String, Integer> customFieldDic = null;

//        if (enableCustomCols)
//        {
//            customFieldsPropertyInfo = GetCustomFieldDicProperty<T>();
//            if (null != customFieldsPropertyInfo)
//            {
//                importCustomFields = true;
//                customFieldDic = new Dictionary<String, int>();
//            }
//        }

//        Dictionary<String, ExcelImportField> fieldDic = GetColumnHead();
        Map<String, Integer> importFieldDic = new HashMap<>();
        return true;
    }

    public class ExcelImportField extends ExcelExportableField{

    }

    public class ExcelExportableField
    {
        private String key;

        private String name;

        private String desc;

        private int order;
        public Boolean required;

    }
}

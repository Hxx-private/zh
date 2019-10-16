package com.zh.readexcel;

import com.zh.entity.CellMappingModel;
import com.zh.entity.TagMappingModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @program: readexcel
 * @description:
 * @author: zxb
 * @create: 2019-10-12 15:23
 **/
@Slf4j
public class readExcel {
    public void write() throws Exception {
//        Workbook excel1997 = new HSSFWorkbook(); // excel 1997
//        FileOutputStream fileOut = new FileOutputStream("workbook.xls");
//        excel1997.write(fileOut);
//        fileOut.close();

        Workbook wb = new XSSFWorkbook(); // excel 2007
        CreationHelper createHelper = wb.getCreationHelper();
        //创建excel文件
        FileOutputStream fileOut = new FileOutputStream("workbook.xlsx");
        //创建一个工作表
        Sheet sheet1 = wb.createSheet("new sheet");
        //向工作表里添加数据
        //创建列
        Row row = sheet1.createRow((short) 0);
        //创建行
        Cell cell = row.createCell(0);
        cell.setCellValue(1);
        row.createCell(1).setCellValue(1.2);
        row.createCell(2).setCellValue(
                createHelper.createRichTextString("This is a string"));
        row.createCell(3).setCellValue(true);
        wb.write(fileOut);
        fileOut.close();
    }


    public Map<Integer, TagMappingModel> TagMappingread(String filename) throws Exception {

        InputStream inputStream = null;
        int j = 0;

        try {
            //打开excel文件
            inputStream = this.getClass().getResourceAsStream("/static/" + filename);
            //创建一个表格输入流
            Workbook wb = WorkbookFactory.create(inputStream);
            //获取第一张工作表
            Sheet sheet = wb.getSheetAt(0);
            //Row类型的迭代器  用于遍历访问每一行
            Iterator<Row> rowIterator = sheet.rowIterator();
            Map<Integer, TagMappingModel> map = new HashMap<>();
            //循环迭代开始
            while (rowIterator.hasNext()) {
                System.out.println("第j:" + j + "次循环开始");
                //跳过前四行
                if (j < 5) {
                    System.out.println("该行已经跳过");
                    j++;
                    //读取一列
                    Row r = rowIterator.next();
                    continue;
                }

                //读取一列
                Row r = rowIterator.next();

                //判断是否为空，若为空 则进入下一列
                if (r == null) {
                    System.out.println("Empty");
                    continue;
                }

                TagMappingModel tagMappingModel = new TagMappingModel();
                //开始循环遍历行中的每一列
                for (int i = r.getFirstCellNum(); i < r.getLastCellNum(); i++) {
                    //读取一个单元格
                    Cell cell = r.getCell(i);
                    //定义一个存放数据的变量
                    String cellValue = "";

                    System.out.println("第i:" + i + "次循环开始");

                    //判断单元格数据类型
                    switch ((cell.getCellType())) {
                        case STRING:
                            cellValue = cell.getRichStringCellValue().getString();
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                cellValue = cell.getDateCellValue().toString();
                            } else {
                                cellValue = String.valueOf(cell.getNumericCellValue());
                            }
                            break;
                        case BOOLEAN:
                            cellValue = String.valueOf(cell.getBooleanCellValue());
                            break;
                        case FORMULA:
                            cellValue = String.valueOf(cell.getCellFormula());
                            break;
                        case BLANK:
                            break;
                        default:
                    }

                    //打印数据
                    System.out.println("CellNum:" + i + " => CellValue:" + cellValue);

                    switch (i) {
                        case 0:
                            tagMappingModel.buildingId = cellValue;
                            break;
                        case 1:
                            tagMappingModel.floorId = cellValue;
                            break;
                        case 2:
                            tagMappingModel.roomId = cellValue;
                            break;
                        case 3:
                            tagMappingModel.deviceId = cellValue;
                            break;
                        case 4:
                            tagMappingModel.deviceName = cellValue;
                            break;
                        case 5:
                            tagMappingModel.deviceType = cellValue;
                            break;
                        case 6:
                            tagMappingModel.deviceMapString = cellValue;
                            break;
                        case 7:
                            tagMappingModel.tagKey = cellValue;
                            break;
                        case 8:
                            tagMappingModel.tagId = cellValue;
                            break;
                        case 9:
                            tagMappingModel.tagName = cellValue;
                            break;
                        case 10:
                            tagMappingModel.tagUnit = cellValue;
                        default:
                    }
                }
                map.put(j, tagMappingModel);
                System.out.println(" map:" + map.get(j));
                j++;

            }
            return map;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }


    }


    public Map<Integer, CellMappingModel> CellMappingread(String filename) throws Exception {

        InputStream inputStream = null;
        int j = 0;

        try {
            //打开excel文件
            inputStream = this.getClass().getResourceAsStream("/static/" + filename);
            //创建一个表格输入流
            Workbook wb = WorkbookFactory.create(inputStream);
            //获取第一张工作表
            Sheet sheet = wb.getSheetAt(0);
            //Row类型的迭代器  用于遍历访问每一行
            Iterator<Row> rowIterator = sheet.rowIterator();
            Map<Integer, CellMappingModel> map = new HashMap<>();
            //循环迭代开始
            while (rowIterator.hasNext()) {
                //System.out.println("第j:" + j + "次循环开始");
                //跳过前四行
                if (j < 5) {
                    System.out.println("该行已经跳过");
                    j++;
                    //读取一列
                    Row r = rowIterator.next();
                    continue;
                }

                //读取一列
                Row r = rowIterator.next();

                //判断是否为空，若为空 则进入下一列
                if (r == null) {
                    System.out.println("Empty");
                    continue;
                }

                CellMappingModel cellMappingModel = new CellMappingModel();
                //开始循环遍历行中的每一列
                for (int i = r.getFirstCellNum(); i < r.getLastCellNum(); i++) {
                    //读取一个单元格
                    Cell cell = r.getCell(i);
                    //定义一个存放数据的变量
                    String cellValue = "";

                    //System.out.println("第i:" + i + "次循环开始");

                    //判断单元格数据类型
                    switch ((cell.getCellType())) {
                        case STRING:
                            cellValue = cell.getRichStringCellValue().getString();
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                cellValue = cell.getDateCellValue().toString();
                            } else {
                                cellValue = String.valueOf(cell.getNumericCellValue());
                            }
                            break;
                        case BOOLEAN:
                            cellValue = String.valueOf(cell.getBooleanCellValue());
                            break;
                        case FORMULA:
                            cellValue = String.valueOf(cell.getCellFormula());
                            break;
                        case BLANK:
                            break;
                        default:
                    }

                    //打印数据
                    //System.out.println("CellNum:" + i + " => CellValue:" + cellValue);

                    switch (i) {
                        case 0:
                            cellMappingModel.buildingId = cellValue;
                            break;
                        case 1:
                            cellMappingModel.floorId = cellValue;
                            break;
                        case 2:
                            cellMappingModel.roomId = cellValue;
                            break;
                        case 3:
                            cellMappingModel.cabinetId = cellValue;
                            break;
                        case 4:
                            cellMappingModel.cellId = cellValue;
                            break;
                        case 5:
                            cellMappingModel.cellType = cellValue;
                            break;
                        case 6:
                            cellMappingModel.cellWidth = Double.valueOf(cellValue);
                            break;
                        case 7:
                            cellMappingModel.cellName = cellValue;
                            break;
                        case 8:
                            cellMappingModel.cellMapString = cellValue;
                            break;
                        default:
                    }
                }
                map.put(j, cellMappingModel);
                //System.out.println(" map:" + map.get(j));
                j++;

            }
            return map;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }


    }


    /*InputStream inp = null;
    try {
        inp = new FileInputStream("workbook04.xls");
        Workbook wb = WorkbookFactory.create(inp);
        Sheet sheet = wb.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()) {
            Row r = rowIterator.next();
            if (r == null) {
                System.out.println("Empty Row");
                continue;
            }
            for (int i = r.getFirstCellNum(); i < r.getLastCellNum(); i++) {
                Cell cell = r.getCell(i);
                String cellValue = "";
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        cellValue = cell.getRichStringCellValue().getString();
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            cellValue = cell.getDateCellValue().toString();
                        } else {
                            cellValue = String.valueOf(cell.getNumericCellValue());
                        }
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        cellValue = String.valueOf(cell.getBooleanCellValue());
                        break;
                    case Cell.CELL_TYPE_FORMULA:
                        cellValue = String.valueOf(cell.getCellFormula());
                        break;
                    case Cell.CELL_TYPE_BLANK:
                        break;
                    default:
                }
                System.out.println("CellNum:" + i + " => CellValue:" + cellValue);
            }
        }
    } finally {
        if (inp != null) {
            inp.close();
        }
    }*/
}

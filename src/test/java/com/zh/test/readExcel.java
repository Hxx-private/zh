package com.zh.test;/**
 * ClassName: readExcel <br/>
 * Description: <br/>
 * date: 2019/10/12 下午3:23<br/>
 *
 * @author Hesion<br />
 * @version
 * @since JDK 1.8
 */

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @program: readexcel
 * @description:
 * @author: zxb
 * @create: 2019-10-12 15:23
 **/
public class readExcel {
    @Test
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

    @Test
    public HashMap read() throws Exception {
        InputStream inputStream = null;
        try {

            inputStream = new FileInputStream("CellMapping.xlsx");
            Workbook wb = WorkbookFactory.create(inputStream);
            Sheet sheet = wb.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext()) {
                Row r = rowIterator.next();
                if (r == null) {
                    System.out.println("Empty");
                    continue;
                }
                for (int i = r.getFirstCellNum(); i < r.getLastCellNum(); i++) {
                    Cell cell = r.getCell(i);
                    String cellValue = "";
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
                    HashMap cellMapping = new HashMap<String,String>();
                    cellMapping.put(i,cellValue);
                    System.out.println(cellMapping);
                        return cellMapping;
                    //System.out.println("CellNum:" + i + " => CellValue:" + cellValue);

                }
            }


        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return null;
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

package com.zh.readexcel;


import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;


public class readExcel {

    public static HashMap read() throws Exception {
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
                    HashMap cellMapping = new HashMap<String, String>();
                    cellMapping.put(i, cellValue);
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

}

package com.zh.readexcel;/**
 * ClassName: ExcelUtil <br/>
 * Description: <br/>
 * date: 2019/10/12 下午3:55<br/>
 *
 * @author Hesion<br />
 * @version
 * @since JDK 1.8
 */

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.apache.poi.hssf.usermodel.HSSFClientAnchor.MAX_ROW;

/**
 * @program: readexcel
 * @description:
 * @author: zxb
 * @create: 2019-10-12 15:55
 **/
public class ExcelUtil {

    private final static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * @param fileName       文件名
     * @param inputStream    文件流
     * @param map            对象中的属性名
     *                       Map<Integer, String> map = new HashMap<>();
     *                       map.put(0, "id");
     *                       map.put(1, "name");
     *                       map.put(2, "age");
     * @param dataStartIndex 开始行
     * @param clazz          对象
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> List<T> readExcel(String fileName, InputStream inputStream, Map<Integer, String> map,
                                        int dataStartIndex, Class<T> clazz) throws Exception {
        //获取文件名后缀判断文件类型
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1,
                fileName.length());
        //根据文件类型及文件输入流新建工作簿对象
        Workbook wb = null;
        List<T> list = new ArrayList<>();
        if (fileType.equals("xls")) {
            wb = new HSSFWorkbook(inputStream);
        } else if (fileType.equals("xlsx")) {
            wb = new XSSFWorkbook(inputStream);
        } else {
            logger.error("您上传的excel格式不正确");
            throw new Exception("您上传的excel格式不正确");
        }

        //获取要取的数据列
        Set<Integer> keySet = map.keySet();
        // 获取第一个Sheet表
        Sheet hssfSheet = wb.getSheetAt(0);

        //设置默认最大行数,当超出最大行数时返回异常
        if (hssfSheet != null && hssfSheet.getLastRowNum() > MAX_ROW) {
            throw new Exception("Excel 数据超过20000行,请检查是否有空行,或分批导入");
        }
        logger.info("readExcel {}", hssfSheet.getLastRowNum());
        // 遍历Excel中的每一行
        for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
            //获取当前行
            Row hssfRow = hssfSheet.getRow(rowNum);
            if (rowNum >= dataStartIndex) {
                //实例化反射类对象
                T obj = clazz.newInstance();
                //当前行数据为空时，跳出本次循环进入下一行
                boolean flag = false;
                Iterator<Integer> it = keySet.iterator();
                while (it.hasNext()) {
                    Integer key = it.next();
                    if (key == null) {
                        throw new Exception("配置map key不能为null");
                    } else if (key >= 0 && key < hssfRow.getLastCellNum()) {
                        //得到属性名
                        String attrName = map.get(key);
                        //得到属性类型
                        Class<?> attrType = BeanUtils.findPropertyType(attrName,
                                new Class[]{obj.getClass()});
                        //得到属性值
                        Cell cell = hssfRow.getCell(key);
                        if (cell != null && !("").equals(cell.toString().trim())) {
                            Object val = getValue(cell, attrType);
                            if (attrType == String.class && !((String) val).isEmpty()) {
                                setter(obj, attrName, val, attrType, rowNum, key, attrName);
                                flag = true;
                            }
                        }
                    } else {
                        logger.warn("导入模板非法");
                    }
                }
                if (!flag) {
                    break;
                }
                list.add(obj);
            }
        }
        return list;
    }

    /**
     * <p>
     * Description:读取当前单元格的值<br />
     * </p>
     *
     * @param cell     单元格对象
     * @param attrType 属性类型
     * @return val 当前单元格的值
     * @throws Exception
     * @since JDK 1.7
     */
    public static Object getValue(Cell cell, Class<?> attrType) throws Exception {
        //新建当前单元格值对象
        Object val = null;
        //判断当前单元格数据类型并取值
        if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
            val = cell.getBooleanCellValue();

        } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
            if (DateUtil.isCellDateFormatted(cell)) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (attrType == String.class) {
                    val = sdf.format(DateUtil
                            .getJavaDate(cell.getNumericCellValue()));
                } else {
                    val = DateUtil.convertTime(
                            sdf.format(DateUtil.getJavaDate(
                                    cell.getNumericCellValue())));
                }
            } else {
                if (attrType == String.class) {
                    cell.setCellType(CellType.STRING);
                    val = cell.getStringCellValue();
                } else if (attrType == BigDecimal.class) {
                    val = new BigDecimal(cell.getNumericCellValue());
                } else if (attrType == long.class) {
                    val = (long) cell.getNumericCellValue();
                } else if (attrType == Double.class) {
                    val = cell.getNumericCellValue();
                } else if (attrType == Float.class) {
                    val = (float) cell.getNumericCellValue();
                } else if (attrType == int.class || attrType == Integer.class) {
                    val = (int) cell.getNumericCellValue();
                } else if (attrType == Short.class) {
                    val = (short) cell.getNumericCellValue();
                } else {
                    val = cell.getNumericCellValue();
                }
            }

        } else if (cell.getCellTypeEnum() == CellType.STRING) {
            val = cell.getStringCellValue();
        }
        return val;
    }

    /**
     * <p>
     * Description:setter(反射set方法给属性赋值)<br />
     * </p>
     *
     * @param obj       反射类对象
     * @param attrName  属性名
     * @param attrValue 属性值
     * @param attrType  属性类型
     * @param row       当前数据在Excel中的具体行数
     * @param column    当前数据在Excel中的具体列数
     * @param key       当前数据对应的Excel列名
     * @throws Exception void
     * @since JDK 1.7
     */
    public static void setter(Object obj, String attrName, Object attrValue,
                              Class<?> attrType, int row, int column, Object key) throws Exception {
        try {
            //获取反射的方法名
            Method method = obj.getClass().getMethod(
                    "set" + StringUtils.toUpperCaseFirstOne(attrName), attrType);//取属性名，将首个字母设为大写，拼接set方法
            //进行反射
            method.invoke(obj, attrValue);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("第" + (row + 1) + " 行  " + (column + 1) + "列   属性：" + key
                    + " 赋值异常  " + e.getStackTrace());
            throw new Exception("第" + (row + 1) + " 行  " + (column + 1) + "列   属性："
                    + key + " 赋值异常  ");
        }

    }
}

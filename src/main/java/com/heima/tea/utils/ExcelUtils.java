package com.heima.tea.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JackieZhu
 * @date 2018/03/28
 * 处理Excel的工具类
 */
public class ExcelUtils {
    private static final String EXCEL2003L = ".xls";
    private static final String EXCEL2007H = ".xlsx";
    /**
     * 读取IO流中的数据,并将每一行数据封装为List<Object>,多行数据封装为List<List<Object>>
     * @param in              输入流
     * @param filename        文件名称
     * @return 多行数据
     */
    public static List<List<Object>> getDataFromExcel(InputStream in,String filename) throws IOException {
        List<List<Object>> data = new ArrayList<>();
        //根据不同的后缀名创建不同的工作簿对象
        Workbook workbook = getWorkbook(in,filename);
        if(workbook==null){
            throw new RuntimeException("创建的工作簿对象失败");
        }
        //从创建的工作簿对象中获取sheet,row,cell
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        //1:获取Excel文件中的所有行
        for(int i=0;i<workbook.getNumberOfSheets();i++){
            sheet = workbook.getSheetAt(i);
            if(sheet==null){
                continue;
            }
            //遍历所有的行
            for (int j=sheet.getFirstRowNum();j<sheet.getLastRowNum();j++){
                row = sheet.getRow(j);
                if(row==null){
                    continue;
                }
                List<Object> cellList = new ArrayList<>();
                for(int k=row.getFirstCellNum();k<row.getLastCellNum();k++){
                    cell = row.getCell(k);
                    cellList.add(getCellValue(cell));
                }
                data.add(cellList);
            }
        }
        return data;
    }

    /**
     * 获取单元格中的数据值,并对其进行格式化
     * @param cell
     * @return
     */
    private static Object getCellValue(Cell cell) {
        Object value = null;
        //格式化number String字符
        DecimalFormat df = new DecimalFormat("0");
        //日期格式化
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //格式化数字
        DecimalFormat df2 = new DecimalFormat("0.00");

        if (null != cell) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    value = cell.getRichStringCellValue().getString();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    if("General".equals(cell.getCellStyle().getDataFormatString())){
                        value = df.format(cell.getNumericCellValue());
                    }else if("m/d/yy".equals(cell.getCellStyle().getDataFormatString())){
                        value = sdf.format(cell.getDateCellValue());
                    }else{
                        value = df2.format(cell.getNumericCellValue());
                    }
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    value = cell.getBooleanCellValue();
                    break;
                case Cell.CELL_TYPE_BLANK:
                    value = "";
                    break;
                default:
                    break;
            }
        }
        return value;
    }

    /**
     * 根据文件名称和输入流创建workbook对象
     * @param in
     * @param filename
     * @return
     * @throws IOException
     */
    private static Workbook getWorkbook(InputStream in, String filename) throws IOException {
        Workbook workbook = null;
        //截取文件的后缀名
        String filetype = filename.substring(filename.lastIndexOf("."));
        //判断该文件的版本
        if(EXCEL2003L.equals(filetype)){
            //该文件为2003版本一下,创建HSSFWorkbook对象
            workbook = new HSSFWorkbook(in);
        }else if (EXCEL2007H.equals(filetype)){
            //该文件版本为2007版本以上,创建XSSFWorkbook对象
            workbook = new XSSFWorkbook(in);
        }else{
            throw new RuntimeException("解析的文件后缀名称有误");
        }
        return workbook;
    }


}

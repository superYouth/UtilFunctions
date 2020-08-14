package com.zhenxuan.utils.poi;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class POIUtils {
    //创建工作薄
    public static HSSFWorkbook createWorkBook(InputStream in) throws IOException {
        POIFSFileSystem fs = new POIFSFileSystem(in);
        return new HSSFWorkbook(fs);
    }

    //获取sheet
    public static HSSFSheet getSheet(HSSFWorkbook workbook,int index){
        return workbook.getSheetAt(index);
    }

    //获取sheet
    public static HSSFSheet getSheet(HSSFWorkbook workbook, String sheetName){
        return workbook.getSheet(sheetName);
    }

    public static String[][] readInfoFromExcel(InputStream in,String sheetName) throws IOException{
        HSSFWorkbook workbook = createWorkBook(in);
        HSSFSheet sheet = null;
        if(StringUtils.isNotBlank(sheetName)){
            sheet = getSheet(workbook,sheetName);
        }else{
            sheet = getSheet(workbook,0);
        }

        //获最后一行
        int rowend = sheet.getLastRowNum();
        String[][] info = new String[rowend][7];

        for(int i = 0; i < rowend; i++){
            HSSFRow row = sheet.getRow(i + 1);
            for(int j = 0 ; j < 7; j++){
                HSSFCell cell = row.getCell(j);
                info[i][j] = getCellFormatValue(cell);
            }
        }

        return info;

    }

    public static String getCellFormatValue(HSSFCell cell) {
        String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
                // 如果当前Cell的Type为NUMERIC
                case HSSFCell.CELL_TYPE_NUMERIC:
                case HSSFCell.CELL_TYPE_FORMULA: {
                    // 判断当前的cell是否为Date
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        Date date = cell.getDateCellValue();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        cellvalue = sdf.format(date);
                    }
                    // 如果是纯数字
                    else {
                        // 取得当前Cell的数值
                        cellvalue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                // 如果当前Cell的Type为STRIN
                case HSSFCell.CELL_TYPE_STRING:
                    // 取得当前的Cell字符串
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                // 默认的Cell值
                default:
                    cellvalue = " ";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;
    }


}

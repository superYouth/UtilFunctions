package com.zhenxuan.utils.poi;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;

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
    public static Sheet getSheet(Workbook workbook,int index){
        return workbook.getSheetAt(index);
    }

    //获取sheet
    public static Sheet getSheet(Workbook workbook, String sheetName){
        return workbook.getSheet(sheetName);
    }

    public static String[][] readInfoFromExcel(InputStream in,String sheetName) throws IOException{
//        HSSFWorkbook workbook = createWorkBook(in);
        Workbook workbook = WorkbookFactory.create(in);
        Sheet sheet = null;
        if(StringUtils.isNotBlank(sheetName)){
            sheet = getSheet(workbook,sheetName);
        }else{
            sheet = getSheet(workbook,0);
        }

        //获最后一行
        int rowend = sheet.getLastRowNum();
        String[][] info = new String[rowend][7];

        for(int i = 0; i < rowend; i++){
            Row row = sheet.getRow(i + 1);
            for(int j = 0 ; j < 7; j++){
                Cell cell = row.getCell(j);
                info[i][j] = getCellFormatValue(cell);
            }
        }
        System.out.println("=========================");
        for (int i = 0; i < info.length; i++) {
            for (int j = 0; j < info[i].length; j++) {
                System.out.println(info[i][j]);
            }

        }

        return info;

    }

    public static String getCellFormatValue(Cell cell) {
        String cellvalue = "";
        if (cell != null) {
//            System.out.println(cell.getCellType());
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
                // 如果当前Cell的Type为NUMERIC
                case NUMERIC:
                case FORMULA: {
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
                case STRING:
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

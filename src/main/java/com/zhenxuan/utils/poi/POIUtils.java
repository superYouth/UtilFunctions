package com.zhenxuan.utils.poi;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class POIUtils {
//    //创建工作薄
//    public static HSSFWorkbook createWorkBook(InputStream in) throws IOException {
//        POIFSFileSystem fs = new POIFSFileSystem(in);
//        return new HSSFWorkbook(fs);
//    }

    //根据sheet name获取sheet
    public static Sheet getSheet(Workbook workbook, int index) {
        return workbook.getSheetAt(index);
    }

    //根据index获取sheet
    public static Sheet getSheet(Workbook workbook, String sheetName) {
        return workbook.getSheet(sheetName);
    }

    public static Workbook getWorkbook(File excelFile) throws IOException {
        // POI 4.1.1支持的方式：
        String fileName = excelFile.getName();
        InputStream in = new FileInputStream(excelFile);
        Workbook workbook = WorkbookFactory.create(in);
        // POI 3.x支持的方式：
//        Workbook workbook = null;
//        if (fileName.endsWith(".xls")) {
//            workbook = new HSSFWorkbook(in);
//        } else if (fileName.endsWith(".xlsx") || fileName.endsWith(".xlsm")) {
//            workbook = new XSSFWorkbook(in);
//        } else {
//            throw new RuntimeException("不支持的文件格式!");
//        }
        return workbook;
    }


    private static String getCellData(Sheet sheet, int row, int col) {
        String cellData = null;

        try {
            Cell cell = sheet.getRow(row).getCell(col);

            switch (cell.getCellType()) {
                case STRING:
                    cellData = sheet.getRow(row).getCell(col).getStringCellValue().trim();
                    break;
                case NUMERIC:
                case FORMULA: {
                    // 判断当前的cell是否为Date
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
//                        DataFormatter df = new DataFormatter();
//                        cellData = df.formatCellValue(cell);
                        Date date = cell.getDateCellValue();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        cellData = sdf.format(date);
                    }else {
                        // 取得当前Cell的数值
                        cellData = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case BOOLEAN:
                    cellData = String.valueOf(sheet.getRow(row).getCell(col).getBooleanCellValue());
                    break;
                default:
                    cellData = sheet.getRow(row).getCell(col).getStringCellValue().trim();
            }
        } catch (Exception ex) {

        }

        return cellData;
    }


}

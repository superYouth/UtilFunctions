package com.zhenxuan.utils.poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class POIUtils {

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
/*        Workbook workbook = null;
        if (fileName.endsWith(".xls")) {
            workbook = new HSSFWorkbook(in);
        } else if (fileName.endsWith(".xlsx") || fileName.endsWith(".xlsm")) {
            workbook = new XSSFWorkbook(in);
        } else {
            throw new RuntimeException("不支持的文件格式!");
        }*/
        return workbook;
    }

    public static String getCellData(Sheet sheet, int row, int col) {
        Row rowObj = sheet.getRow(row);
        return getCellData(rowObj, col);
    }

    public static String getCellData(Row row, int col) {
        Cell cell = row.getCell(col);
        return getCellData(cell);
    }

    public static String getCellData(Cell cell) {
        String cellData = null;
        try {
            switch (cell.getCellType()) {
                case STRING:
                    cellData = cell.getStringCellValue().trim();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        // Date 类型
                        cellData = cell.getDateCellValue().toString();
                    } else {
                        // 数字类型
                        // 注：cell.getNumericCellValue()获取浮点型数字可能会有精度问题，或者会省略数字无效的0
                        DataFormatter df = new DataFormatter();
                        cellData = df.formatCellValue(cell);
                    }
                    break;
                case FORMULA: {
                    cellData = cell.getCellFormula();
                    break;
                }
                case BOOLEAN:
                    cellData = String.valueOf(cell.getBooleanCellValue());
                    break;
                default:
                    cellData = "";
            }
        } catch (Exception ex) {

        }

        return cellData;
    }


}

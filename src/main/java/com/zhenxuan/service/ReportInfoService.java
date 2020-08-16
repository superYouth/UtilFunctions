package com.zhenxuan.service;

import com.zhenxuan.entity.CheckItemDetail;
import com.zhenxuan.utils.poi.POIUtils;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ReportInfoService {

    public Object getExcelInfo() throws IOException {

        String path = "D:\\data\\CSR 簡易版.xlsx";
        FileInputStream fis = new FileInputStream(path);

        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sheet = workbook.getSheetAt(0);

        int colEnd = 2;
        //获最后一行
        int rowEnd = sheet.getLastRowNum();
        System.out.println("rowEnd:"+rowEnd);
        String[][] info = new String[rowEnd][colEnd];

        List<CheckItemDetail> configList = Lists.newArrayList();
        CheckItemDetail checkItemDetail = null;
        for (int i = 0; i < rowEnd+1; i++) {

            Row row = sheet.getRow(i);
            Cell cell = row.getCell(0);
            if(i == rowEnd-1){
                int oo = 0;
            }
            if(cell != null && CellType.NUMERIC.equals(cell.getCellType())){
                configList.add(checkItemDetail);
                String SeqValue = String.valueOf(cell.getNumericCellValue());
                String desc = String.valueOf(row.getCell(1).getStringCellValue()).trim();

                String[] numArr = SeqValue.split("\\.");
                checkItemDetail = new CheckItemDetail();
                checkItemDetail.setGroupSeq(Integer.valueOf(numArr[0]));
                checkItemDetail.setCheckItemSeq(Integer.valueOf(numArr[1]));
                checkItemDetail.setDesc(checkItemDetail.getDesc().concat(desc));
            }else{
                String desc = String.valueOf(row.getCell(1).getStringCellValue()).trim();
                checkItemDetail.setDesc(checkItemDetail.getDesc().concat(desc));
                if(i == rowEnd){
                    configList.add(checkItemDetail);
                }
            }

        }
        configList.forEach( element ->{
            System.out.println(element);
        });
        return "";
    }


    public static void main(String[] args) throws IOException {
        ReportInfoService reportInfoService = new ReportInfoService();
        reportInfoService.getExcelInfo();
    }


}

package com.zhenxuan.service;

import com.alibaba.fastjson.JSON;
import com.zhenxuan.entity.AuditItemConfig;
import com.zhenxuan.entity.CheckGroupData;
import com.zhenxuan.entity.CheckItemData;
import com.zhenxuan.entity.CheckItemDesc;
import org.apache.commons.compress.utils.Lists;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ReportInfoService {

    public Object getExcelInfo() throws IOException {

        File excelFile = ResourceUtils.getFile("classpath:textfiles/CSR.xls");

        FileInputStream fis = new FileInputStream(excelFile);

        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sheet = workbook.getSheetAt(3);

        int colEnd = 2;
        int rowStart = 9;
        //获最后一行
        int rowEnd = sheet.getLastRowNum();
        System.out.println("rowEnd:" + rowEnd);

        AuditItemConfig auditItemConfig = new AuditItemConfig();
        List<CheckGroupData> groupList = Lists.newArrayList();
        CheckGroupData checkGroupData = new CheckGroupData();

        List<CheckItemData> resultList = getConfig(sheet, rowStart, rowEnd);

        String result = JSON.toJSONString(resultList);
        System.out.println(result);
        return "";
    }

    private List<CheckItemData> getConfig(Sheet sheet, int rowIndex, int rowEnd){
        List<CheckItemData> itemConfigList = Lists.newArrayList();

        CheckItemData checkItemData = null;
        do {
            Row row = sheet.getRow(rowIndex);
            Cell cell = row.getCell(1);
            if (cell != null && CellType.NUMERIC.equals(cell.getCellType())) {

                String SeqValue = String.valueOf(cell.getNumericCellValue());
                String desc = String.valueOf(row.getCell(2).getStringCellValue()).trim();
                String[] numArr = SeqValue.split("\\.");

                checkItemData = new CheckItemData();
                checkItemData.setItem_threshold_score(3);
                checkItemData.setPhoto_max_count(3);
                List<String> scoreList = Lists.newArrayList();
                scoreList.add("N/A");
                scoreList.add("1");
                scoreList.add("2");
                scoreList.add("3");
                scoreList.add("4");
                scoreList.add("5");
                checkItemData.setItem_score_list(scoreList);
                checkItemData.setCheck_item_seq(Integer.valueOf(numArr[1]));
                CheckItemDesc checkItemDesc = new CheckItemDesc();
                checkItemDesc.setEn(desc);
                checkItemDesc.setZh("");
                checkItemData.setCheck_item_desc(checkItemDesc);

            } else {
                String desc = String.valueOf(row.getCell(2).getStringCellValue()).trim();
                CheckItemDesc check_item_desc = checkItemData.getCheck_item_desc();
                Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");
                Matcher matcher = pattern.matcher(desc);
                if(matcher.find()){
                    check_item_desc.setZh(check_item_desc.getZh().concat(desc));
                }else{
                    check_item_desc.setEn(check_item_desc.getEn().concat(desc));
                }
            }
            if (rowIndex >= 61) {
                System.out.println("haha");
            }
            Cell nextRowSeqCell = sheet.getRow(rowIndex + 1).getCell(1);
            Cell nextRowDescCell = sheet.getRow(rowIndex + 1).getCell(2);
            if (nextRowSeqCell != null && CellType.NUMERIC.equals(nextRowSeqCell.getCellType())) {
                itemConfigList.add(checkItemData);
            }
            if(CellType.BLANK.equals(nextRowDescCell.getCellType())){
                break;
            }

            rowIndex++;
        } while (rowIndex <= rowEnd);

        return itemConfigList;
    }


    public static void main(String[] args) throws IOException {
        ReportInfoService reportInfoService = new ReportInfoService();
        reportInfoService.getExcelInfo();
    }


}

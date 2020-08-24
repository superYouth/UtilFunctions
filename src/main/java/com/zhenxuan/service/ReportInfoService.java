package com.zhenxuan.service;

import com.alibaba.fastjson.JSON;
import com.zhenxuan.entity.AuditItemConfig;
import com.zhenxuan.entity.CheckGroupData;
import com.zhenxuan.entity.CheckItemData;
import com.zhenxuan.entity.CheckItemDesc;
import com.zhenxuan.utils.poi.POIUtils;
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
        Workbook workbook = POIUtils.getWorkbook(excelFile);


        AuditItemConfig auditItemConfig = new AuditItemConfig();
        List<CheckGroupData> groupList = Lists.newArrayList();

        for (int i = 3; i <14 ; i++) {
            System.out.println("sheet index: "+i);
            Sheet sheet = workbook.getSheetAt(i);
            List<CheckItemData> resultList = getConfig(sheet);
            CheckGroupData checkGroupData = new CheckGroupData();
            checkGroupData.setCheck_item_data(resultList);
            groupList.add(checkGroupData);
        }
        auditItemConfig.setCheck_group_data(groupList);

        
        String result = JSON.toJSONString(auditItemConfig);
        System.out.println(result);
        return "";
    }

    private List<CheckItemData> getConfig(Sheet sheet) {

        int rowStart = 9;
        //获最后一行
        int rowEnd = sheet.getLastRowNum();
        List<CheckItemData> itemConfigList = Lists.newArrayList();

        CheckItemData checkItemData = null;
        for (int i = rowStart; i < rowEnd; i++) {
            Row row = sheet.getRow(i);
            Cell cell = row.getCell(1);
            CellStyle cellStyle = cell.getCellStyle();
            BorderStyle borderBottom = cellStyle.getBorderBottom();
            System.out.println(borderBottom);

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
                if (matcher.find()) {
                    check_item_desc.setZh(check_item_desc.getZh().concat(desc));
                } else {
                    check_item_desc.setEn(check_item_desc.getEn().concat(" "+desc));
                }
            }
            if (borderBottom.equals(BorderStyle.DASHED)) {
                itemConfigList.add(checkItemData);
            }
            // 结束扫描
            if (BorderStyle.THIN.equals(borderBottom)) {
                break;
            }

        }

        return itemConfigList;
    }


    public static void main(String[] args) throws IOException {
        ReportInfoService reportInfoService = new ReportInfoService();
        reportInfoService.getExcelInfo();
    }


}

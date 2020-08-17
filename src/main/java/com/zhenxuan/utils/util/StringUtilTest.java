package com.zhenxuan.utils.util;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtilTest {

    public static void main(String[] args) throws IOException {
//        File file = new File("C:\\Users\\wh1909006\\Desktop\\新建文件夹\\QPA_COMMON.txt");

        FileReader fr = new FileReader("C:\\Users\\wh1909006\\Desktop\\新建文件夹\\QPA_COMMON.txt");
        BufferedReader br = new BufferedReader(fr);
        String configStr = "";
        String line = null;
        while ((line = br.readLine()) != null) {
            configStr += line;
        }

//        configStr.replace("\\t","");
//        configStr.replace("\\r\\n","");

        configStr = configStr.replaceAll("\r|\n", "");
        configStr = configStr.replaceAll("\\s{2}", "");


//        Pattern p = Pattern.compile("\\s*|\t|");
//        Matcher m = p.matcher(configStr);
//        configStr = m.replaceAll("");
//        FileWriter fw = new FileWriter("C:\\Users\\wh1909006\\Desktop\\新建文件夹\\QPA_COMMON111.txt");
//        BufferedWriter bw = new BufferedWriter(fw);
//        bw.write(configStr,0,configStr.length());
        System.out.println(configStr);
    }
}

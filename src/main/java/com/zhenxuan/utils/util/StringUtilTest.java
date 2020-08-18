package com.zhenxuan.utils.util;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtilTest {

    @Test
    public void compressString() throws ScriptException, IOException {

        String configStr = "";
        File inputFile = ResourceUtils.getFile("classpath:textfiles/inputFile.txt");
        File outputFile = ResourceUtils.getFile("classpath:textfiles/outputFile.txt");

        InputStream is = new FileInputStream(inputFile);
        InputStreamReader isr = new InputStreamReader(is,"UTF8");

        OutputStream os = new FileOutputStream(outputFile);
//        OutputStreamWriter osw = new OutputStreamWriter()

        int read = isr.read();
        System.out.println(read);

//
//        FileReader fr = new FileReader(isr);
//        BufferedReader br = new BufferedReader(fr);
//        String configStr = "";
//        String line = null;
//        while ((line = br.readLine()) != null) {
//            configStr += line;
//        }
//
//        String str = "hahaha\r\nheihei\n";
//        String str1 = "{" +
//                "\"name\": \"Park    er\"" +"\t\r\n"+
//                "}";
//        System.out.println(str1);
//        ScriptEngineManager manager = new ScriptEngineManager();
//        ScriptEngine script = manager.getEngineByName("javascript");
//        Object eval = script.eval("JSON.stringify(" + str1 + ")");
//        System.out.println(eval.toString());

    }

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

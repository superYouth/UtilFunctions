package com.zhenxuan.utils.util;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
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

    @Test
    public void StringTest() throws ScriptException {

        String str = "hahaha\r\nheihei\n";
        String str1 = "{" +
                "\"name\": \"Park    er\"" +"\t\r\n"+
                "}";
        System.out.println(str1);
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine script = manager.getEngineByName("javascript");
        Object eval = script.eval("JSON.stringify(" + str1 + ")");
        System.out.println(eval.toString());

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

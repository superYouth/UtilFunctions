package com.zhenxuan.utils.util;

import org.junit.Test;
import org.springframework.util.ResourceUtils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtilTest {

    @Test
    public void compressJsonString() throws ScriptException, IOException {

        StringBuilder configStr = new StringBuilder();
        File inputFile = ResourceUtils.getFile("classpath:textfiles/inputFile.txt");
        File outputFile = ResourceUtils.getFile("classpath:textfiles/outputFile.txt");
        // 方式一：使用转换流
        InputStream is = new FileInputStream(inputFile);
        InputStreamReader isr = new InputStreamReader(is,"UTF8");

        // 方式二：或者使用FileReader
//        FileReader fr = new FileReader(inputFile);

        BufferedReader br = new BufferedReader(isr);
        String line = null;
        while ((line = br.readLine()) != null) {
            configStr.append(line);
        }
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine script = manager.getEngineByName("javascript");
        String result = (String) script.eval("JSON.stringify(" + configStr.toString() + ")");
        System.out.println(result);

        // 使用转换流写入（也可以用FileWriter）
        OutputStream os = new FileOutputStream(outputFile,false);
        OutputStreamWriter osw = new OutputStreamWriter(os,"UTF8");
        osw.write(result);
        osw.flush();
        osw.close();
    }

    @Test
    public void regexString() throws ScriptException, IOException {

        String configStr = "{\n" +
                "                    \"check_item_seq\": 1,\n" +
                "                    \"check_item_desc\": {\n" +
                "                        \"en\": \"\",\n" +
                "                        \"zh\": \"進料檢驗是否都有SIP﹖\"\n" +
                "                    },\n" +
                "                    \"item_threshold_score\": 1,\n" +
                "                    \"photo_max_count\": 3,\n" +
                "                    \"item_score_list\": [\n" +
                "                        \"N/A\",\n" +
                "                        0,\n" +
                "                        1\n" +
                "                    ]\n" +
                "                }";
        System.out.println("before length: "+configStr.length());

        // 方式一：使用replace
/*        configStr = configStr.replace("\n", "");
        configStr = configStr.replace(" ","");
        System.out.println("after length: "+configStr.length());*/

        // 方式二：使用replaceAll
/*        configStr = configStr.replaceAll("\\s", "");
        System.out.println("after length: "+configStr.length());*/
        // 防止JSON中value值中的空格被替换（多于2个空格的部分会被替换）
//        configStr = configStr.replaceAll("\\s{2}", "");

        // 方式三：使用Pattern
        Pattern pattern = Pattern.compile("\\s*|\t|");
        Matcher matcher = pattern.matcher(configStr);
        configStr = matcher.replaceAll("");
        System.out.println("after length: "+configStr.length());
        System.out.println(configStr);
    }

}

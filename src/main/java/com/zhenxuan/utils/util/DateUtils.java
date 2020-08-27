package com.zhenxuan.utils.util;

import org.springframework.util.StringUtils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {


    private static String transTimeWithTz(Date sourceDate, DateFormat formatter,
                                                       TimeZone sourceTimeZone, TimeZone targetTimeZone) {
        Long targetTime = sourceDate.getTime() - sourceTimeZone.getRawOffset() + targetTimeZone.getRawOffset();
        return "";
    }

    public static void transUtcTimeToCurrentTzTime(String srcTime) throws ScriptException {

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine script = manager.getEngineByName("javascript");
        String result = (String) script.eval("new Date("+srcTime+").toDateString");
        System.out.println(result);
    }


    private static String getCurrentUtcIsoTime(){
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("UTC"));
        String isoDateTime = localDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
        return isoDateTime;
    }
    private static String getCurrentUtcTime(String formatStr){
        if(StringUtils.isEmpty(formatStr)){
            formatStr = "yyyy-MM-dd HH:mm:ss:SSS";
        }
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("UTC"));
        String utcDateTime = localDateTime.format(DateTimeFormatter.ofPattern(formatStr));
        return utcDateTime;
    }



    public static void main(String[] args) throws ScriptException {
        DateUtils.transUtcTimeToCurrentTzTime("2020-08-27");

    }
}

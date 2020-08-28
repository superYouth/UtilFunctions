package com.zhenxuan.utils.util;

import org.springframework.util.StringUtils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    public void test(){
        System.out.println(System.currentTimeMillis());
        System.out.println(LocalDateTime.now());
        System.out.println(Instant.now().getEpochSecond());
        System.out.println(Instant.now());
        System.out.println(new Date().toInstant());
        System.out.println("-------------------------------------");

        System.out.println(LocalDateTime.now().toInstant(ZoneOffset.of("+4")).toEpochMilli());
        System.out.println(LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond());
        System.out.println(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().getEpochSecond());
        System.out.println(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        System.out.println(LocalDateTime.now().atOffset(OffsetDateTime.now().getOffset()).toEpochSecond());
        System.out.println(LocalDateTime.now().atOffset(OffsetDateTime.now().getOffset()).toInstant().getEpochSecond());
        System.out.println(LocalDateTime.now().atOffset(OffsetDateTime.now().getOffset()).toInstant().toEpochMilli());
        System.out.println("========================================");

        Instant instant = Instant.now();
        System.out.println(LocalDateTime.ofInstant(instant, ZoneId.of("UTC")));
        System.out.println(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
        System.out.println(LocalDateTime.now().toInstant(ZoneOffset.of("+4")).toEpochMilli());
        System.out.println(System.currentTimeMillis());
    }

    /**
     *
     * @param epochTime 指定时间和1970-01-01 00:00:00:000之间的毫秒数
     * @param zoneId
     * @return
     */
    public static String transTimeToDestTzTime(Long epochTime, ZoneId zoneId) {
        Instant instant = Instant.ofEpochMilli(epochTime);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
        return localDateTime.toString();
    }

    public static String transTimeToDestTzTime(Long epochTime, String zoneId) {
        return transTimeToDestTzTime(epochTime, ZoneId.of(zoneId));
    }

    public static String transTimeToCurrentTzTime(Long epochTime) {
        return transTimeToDestTzTime(epochTime, ZoneId.systemDefault());
    }

    /**
     * getCurrentUtcIsoTime
     *
     * @return
     * @throws ScriptException
     */
    private static String getCurrentUtcIsoTime() throws ScriptException {
/*        // 1、方式一
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("UTC"));
        String isoDateTime = localDateTime.format(DateTimeFormatter.ISO_DATE_TIME);*/

        // 2、方式二
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine script = manager.getEngineByName("javascript");
        String isoDateTime = (String) script.eval("new Date().toISOString()");
        return isoDateTime;
    }

    /**
     * getCurrentUtcTime
     *
     * @param formatStr
     * @return
     */
    private static String getCurrentUtcTime(String formatStr) {
        if (StringUtils.isEmpty(formatStr)) {
            formatStr = "yyyy-MM-dd HH:mm:ss:SSS";
        }
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("UTC"));
        String utcDateTime = localDateTime.format(DateTimeFormatter.ofPattern(formatStr));
        return utcDateTime;
    }


//    public static void main(String[] args) throws ScriptException {
//
//        String result = DateUtils.transTimeToCurrentTzTime(System.currentTimeMillis());
//        result = DateUtils.transTimeToDestTzTime(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli(),"Asia/Shanghai");
//
//        System.out.println(result);
//    }
}

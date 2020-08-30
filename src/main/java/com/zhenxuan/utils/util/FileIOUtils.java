package com.zhenxuan.utils.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class FileIOUtils {

    // Scanner读取文件
    public void readFile(String filePath){
        try (Scanner sc = new Scanner(new FileReader(filePath))){
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据定界符界定依次读取字符串
     * @param filePath
     * @param delimiter
     */
    public void readFileWithDelimiter(String filePath,String delimiter){
        try (Scanner sc = new Scanner(new FileReader(filePath))){
            sc.useDelimiter("\\|");
            while(sc.hasNext()){
                String str = sc.next();
                System.out.println(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void readFileWithStream(String filePath){
        // 不是全部加载到内存，按行流式加载
        try (Stream<String> lines = Files.lines(Paths.get(filePath))){
            lines.forEachOrdered(element ->{
                System.out.println(element);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 一次性读取文件所有行到内存中
     * @param filePath
     * @throws IOException
     */
    public void readFileAllLines(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
        lines.forEach(System.out::println);

        // 或者
/*        final byte[] bytes = Files.readAllBytes(Paths.get(filePath));
        String content = new String(bytes, StandardCharsets.UTF_8);
        System.out.println(content);*/
    }

    public void readFileWithBuffer(String filePath) {
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = br.readLine()) != null){
                System.out.println(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}

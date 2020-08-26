package com.zhenxuan.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/report")
public class ReportInfoController {

    @PostMapping(path = "/sayHello")
    public Object sayHello() throws IOException {

        return "Hello!";
    }

    /**
     * 单文件上传
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping(path = "/getExcelInfo")
    public Object getExcelInfo(@RequestParam("file") MultipartFile file, @RequestParam("name") String name) throws IOException {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }

        String fileName = file.getOriginalFilename();
        System.out.println("文件大小："+file.getSize());
        System.out.println("name: "+name);

        return "";
    }

    /**
     * 多文件上传
     * @param request
     * @return
     */
    @PostMapping("/uploadFiles")
    public String getExcelInfo(HttpServletRequest request) {

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();

//        poiFun.getExcelData(fileMap);

        return "OK";
    }
}

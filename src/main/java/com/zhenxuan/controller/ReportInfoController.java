package com.zhenxuan.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/report")
public class ReportInfoController {

    @PostMapping(path = "/sayHello")
    public Object sayHello() throws IOException {

        return "Hello!";
    }

    @PostMapping(path = "/getExcelInfo")
    public Object getExcelInfo(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }

        String fileName = file.getOriginalFilename();
        System.out.println("文件大小："+file.getSize());

        return "";
    }
}

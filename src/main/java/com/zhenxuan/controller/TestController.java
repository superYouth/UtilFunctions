package com.zhenxuan.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.*;

@RequestMapping("/test")
@Controller
public class TestController {

    @GetMapping(value = "/getPicture/**", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getPicture(HttpServletRequest request) throws IOException {

//        File file = new File("C:\\Users\\ZHENXUAN\\Desktop\\头像.png");
//        FileInputStream inputStream = new FileInputStream(file);
//        byte[] bytes = new byte[inputStream.available()];
//        inputStream.read(bytes, 0, inputStream.available());
//        return bytes;
        String requestURI = request.getRequestURI();
        String[] uriArr = requestURI.split("getPicture");
        String pictureURI = uriArr[1];
        String picturePath = String.format("D:%s%s", File.separator,pictureURI);
        BufferedImage bufferedImage = ImageIO.read(new FileInputStream(picturePath));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", out);
        return out.toByteArray();



    }
    @GetMapping(path = "/sayHello")
    public Object sayHello() throws IOException {

        return "Hello!";
    }

}

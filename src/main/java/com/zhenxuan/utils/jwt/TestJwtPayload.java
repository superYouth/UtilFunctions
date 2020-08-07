//package com.zhenxuan.utils.jwt;
//
//
//import lombok.Data;
//
//@Data
//public class TestJwtPayload {
//    private String iss;
//
//    private String aud;
//    private String sub;
//
//    private Long iat;
//    private Long exp;
//    private Long nbf;
//}
//
///**
// * iss: 该JWT的签发者，是否使用是可选的；
// * sub: 该JWT所面向的用户，是否使用是可选的；
// * aud: 接收该JWT的一方，是否使用是可选的；
// * exp(expires): 什么时候过期，这里是一个Unix时间戳，是否使用是可选的；
// * iat(issued at): 在什么时候签发的(UNIX时间)，是否使用是可选的；
// * nbf (Not Before)：如果当前时间在nbf里的时间之前，则Token不被接受；一般都会留一些余地，比如几分钟；，是否使用是可选的；
// */

package com.zhenxuan.utils.encryption;

import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 对称加密
 * 对称加密是指，加密方和解密方使用同样的秘钥来进行加密和解密。
 * 在对称加密算法中，数据发信方将明文（原始数据）和加密密钥（mi yue）一起经过特殊加密算法处理后，使其变成复杂的加密密文发送出去。
 * 常用的对称加密算法：AES，RC4，3DES
 */
public class SymmetricalEncryptionTest {


    /*<-----------测试代码-------------->*/
    @Test
    public void test() throws Exception {
        byte[] keyBytes = initKey();
        System.out.println("对称密匙:" + new String(keyBytes, StandardCharsets.UTF_8));//这个密匙解析出来也是乱码,密匙就是一个56byte的byte数组
        String originData = "123456";
        //对原始数据进行加密
        byte[] originDataBytes = originData.getBytes(StandardCharsets.UTF_8);
        byte[] encrpt = encrpt(originDataBytes, keyBytes);
        //进行base64化
        byte[] encode = Base64.getEncoder().encode(encrpt);
        System.out.println("加密然后base64化之后的数据:" + new String(encode, StandardCharsets.UTF_8));
        //解密
        //首先去base64化
        byte[] decode = Base64.getDecoder().decode(encode);
        //使用des进行解密操作
        byte[] deBytes = deEncrpt(decode, keyBytes);
        //对解密后的数据字符串化
        System.out.println(new String(deBytes, StandardCharsets.UTF_8));
    }

    /*<----------加密解密工具------------>*/

    private byte[] initKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        keyGenerator.init(56);
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    }

    private byte[] encrpt(byte[] data, byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, "DES");
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }

    private byte[] deEncrpt(byte[] data, byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, "DES");
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }
}



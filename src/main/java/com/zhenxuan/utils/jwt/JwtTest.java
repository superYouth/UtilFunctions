package com.zhenxuan.utils.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.util.encoders.Base64;

import java.io.StringReader;
import java.security.*;

import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;

public class JwtTest {

    private static String commonKey = "haaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

    private static String publicKeyPem =
            "-----BEGIN PUBLIC KEY-----\n"
                    + "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEKOsxJXkG1WgdMT48+mV6roYKCO3w\n"
                    + "i96Wvejk7KqZdGqb2pG/SMaj5Vz6F5X2cg4xxxMNh0B5lEDt2fNQUQm3wA==\n"
                    + "-----END PUBLIC KEY-----\n";
    private static String privateKeyPem =
            "-----BEGIN EC PRIVATE KEY-----\n"
                    + "MHcCAQEEIDOB7XrPmVHus3UdK+ovouRMnv1jIx/0iJDfLYDQ3OrJoAoGCCqGSM49"
                    + "AwEHoUQDQgAEKOsxJXkG1WgdMT48+mV6roYKCO3wi96Wvejk7KqZdGqb2pG/SMaj"
                    + "5Vz6F5X2cg4xxxMNh0B5lEDt2fNQUQm3wA==\n"
                    + "-----END EC PRIVATE KEY-----";
    private static String rsaPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuqVPZxkEmB5LfxQ8kFJD" +
            "zGIMFTBAehrQ9yULUC1rww8KqGzpO8fatjrvcVB9z00GF/2SHwcznco6CG1ETOgX" +
            "KaQ1sgWnrlitCOpcwC/ECpz4BekowJkBgL0nNpkW17Lt4YbOhMkplkmQEQCQr4dI" +
            "gFxUJO26m/yCjvSvUpj953GEpKHIBv1A/kDcHHI/NppY9FOEsBsM/i+Q4gDfz9zo" +
            "fk352SQxyxH8/McSRJsDsnEKIFld0pZQA+5cVy6YLmZBLo3/vsehOLhAkZ5oNNCU" +
            "W+BWsSh/Jgn7B2tEN2nvecZGrCPDzn20WQ+B8FkOqM1D6JL2Qw8tNzR80ZXSDYoO" +
            "JwIDAQAB";
    private static String rsaPrivateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC6pU9nGQSYHkt/\n" +
            "FDyQUkPMYgwVMEB6GtD3JQtQLWvDDwqobOk7x9q2Ou9xUH3PTQYX/ZIfBzOdyjoI\n" +
            "bURM6BcppDWyBaeuWK0I6lzAL8QKnPgF6SjAmQGAvSc2mRbXsu3hhs6EySmWSZAR\n" +
            "AJCvh0iAXFQk7bqb/IKO9K9SmP3ncYSkocgG/UD+QNwccj82mlj0U4SwGwz+L5Di\n" +
            "AN/P3Oh+TfnZJDHLEfz8xxJEmwOycQogWV3SllAD7lxXLpguZkEujf++x6E4uECR\n" +
            "nmg00JRb4FaxKH8mCfsHa0Q3ae95xkasI8POfbRZD4HwWQ6ozUPokvZDDy03NHzR\n" +
            "ldINig4nAgMBAAECggEAWEWkhvrzDEqPpBtbBVqSzA+27TJz3AvgtnIjppjEtWzg\n" +
            "uoU/zVEY5ER9R9csmSUxc5kuSVRrmf2xcpjSy5ick8ogxwAbrL4tn+DPWul8SxSv\n" +
            "zIPYDTaP2mYbLJw8jGkITjgmZfCJpZVG+HOO+iT+Kg8AkLKKJgnHaXrZjDPlKVbd\n" +
            "BLci2zzKSHvxcP1JJapaRuqj3AMhszyP8L+zmg3hByWxBpGOBiYprsrs8KVgtp4P\n" +
            "g2wVWsHFdSQWIFq1s0KwegA6cFRQE12aidstzFW8SJQb0nLXdboVWHQ+cP7FvYNL\n" +
            "ZVQhDtU/pYnWpc/X3++w91sEXQnDE2h7SOBnNAywwQKBgQDatA2aqhxohcvWYgMI\n" +
            "GVwZpGsIOpWiKtsXwBXPIvb0n6A1uBIBLiYAYB7E5NFF/4ss7HZOlRWnG61KtGXd\n" +
            "OZfoEtTq20ZtM1sp70McxCqOFiOu6Cjkab69S3nYrj5ZQzTzL7pcIrcnrNeLv8tR\n" +
            "LGQQ0Bid9MI4rDQHrlr3c8wTYQKBgQDaebdwqmEPZsnUmhfHunkhB+2d+oOYJmi5\n" +
            "R9ksIbLb+8Muas5gFyat7uycEsACK1R9Rij6J8NHP7Vb5pY7XYgvygHG9OjH7LQm\n" +
            "ySlelt5dcIJVHtlyYBs2UBIJi1tzwKodApbtdP8p6Jt4J3bbIXqDGILWgkz8wnm9\n" +
            "t95bNDGWhwKBgQCRWcEA3bXrsaB7ZsBlDZWsZR3NZkI0eQ8Lhdn4xGAkzEIJ8b/l\n" +
            "kOoorblFWl3Li77PqIgoSeUOHtLZJ3Pbx38x+fIP9JOl18/q2t1brPQrYoNsp1cm\n" +
            "FzFxVUwxufwuDwCN71aIDXp0n+bRNjLGTB5lHih+MkEysSATSsmiVA95gQKBgGrF\n" +
            "w5YHXqolRQxnRqnxA1PWSRXFId0RIGs0pvl/eqcyMqCTyEqD5f5pXy+jpj0pKgCT\n" +
            "cmFXX4OJqjvAxLk8q9sXMakndkCG+UBfXZ2BxCkMXFX3XrQcLhBsL3SEq+w1Q64r\n" +
            "3tEsNawopxqgxuCvSu2Y5BCr265H6TGEE+Kk7nsvAoGAcNkSpg2YmnXlWqLmw95W\n" +
            "JnB98JOBaaXKlYl/Yg5f6fQYOAqrDwlG2cHKNwnoPZeW0O3qtSNM6zx3hOgo36eo\n" +
            "1uyyl7U0OiURoEXft40Jl6bwM4KAWuoj54Btgj6UDIBMzFiV3+f1uYo/zxyxRQ4s\n" +
            "YVrkZ7Lj9ZZ1M9uBTPpAdug=";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public String createJwtTokenWithHs() throws Exception {
        // ==========对称加密token ============================
        JwtBuilder builder = Jwts.builder();
        Date now = new Date();
        builder
                // 配置header
                .setHeaderParam("alg", "H256")
                .setHeaderParam("typ", "JWT")
                // 配置payload
                .setIssuer("zhenxuan.cn")
                .setSubject("2")
                .setAudience("test-app-id")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + 7200 * 1000L));

        return builder.signWith(
                SignatureAlgorithm.HS256,
                commonKey
        ).compact();
    }

    public String createJwtTokenWithRSA() throws Exception {
        JwtBuilder builder = Jwts.builder();
        Date now = new Date();
        builder
                // 配置header
                .setHeaderParam("alg", "H256")
                .setHeaderParam("typ", "JWT")
                // 配置payload
                .setIssuer("zhenxuan.cn")
                .setSubject("2")
                .setAudience("test-app-id")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + 7200 * 1000L));
        // ==========非对称加密token ============================
        byte[] buffer = Base64.decode(rsaPrivateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey secretKey = keyFactory.generatePrivate(keySpec);

        return builder.signWith(
                secretKey,
                SignatureAlgorithm.RS256
        ).compact();
    }

    public String decodeRsaJwt(String jwtToken) throws Exception {
        // ==========非对称加密token 解密============================
        X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.decode(rsaPublicKey));
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(keySpecX509);

        String decodedToken = Jwts.parserBuilder()
                .setSigningKey(pubKey)
                .build()
                .parseClaimsJws(jwtToken).getBody().toString();
        return decodedToken;
    }

    public String decodeHsJwt(String jwtToken) throws Exception {
        // ==========对称加密token 解密========================
        String decodedToken = Jwts.parser()
                .setSigningKey(commonKey)
                .parse(jwtToken)
                .getBody().toString();

        return decodedToken;
    }


    public static void main(String[] args) throws Exception {
        JwtTest jwtTest = new JwtTest();
        String jwtToken = jwtTest.createJwtTokenWithRSA();
        System.out.println("token:::" + jwtToken);
        String decodedJwt = jwtTest.decodeRsaJwt(jwtToken);

        System.out.println(decodedJwt);
    }

}

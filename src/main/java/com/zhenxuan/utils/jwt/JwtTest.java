package com.zhenxuan.utils.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;

import java.io.StringReader;
import java.security.Key;
import java.security.KeyFactory;
import java.security.Security;

import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

public class JwtTest {
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

    static {
        Security.addProvider(new BouncyCastleProvider());
    }


    public String createJwtToken() throws Exception {
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

        PEMParser pemParser = new PEMParser(new StringReader("-----BEGIN PRIVATE KEY-----\n" +
                "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALQBuaVf3pBkG9aA\n" +
                "MsN7dPDCBNT5FX11eyVAqGohHMSNPBx+XhZFIjzYTXg+I4bnCGElpuDRLv6lsYzX\n" +
                "ttwDsWrtZAoO4jEcV0bhh48SDm8F7U1v8S1GGvFXYoQmG5eAYK7UQNROmO2P9r66\n" +
                "Er2MmcvFg0T8yIzVimEbp8+4X525AgMBAAECgYAn2HXquIUAu1g6LBiQkwR4wsnl\n" +
                "BtoN8nJ29ZASJhBDJexXdlWHYnhItay4YDDnDL8bGgWNoGBht/XMcnekziB4Zr4J\n" +
                "2KeM0le/gFspCiicQSuaBUOd6HwgVjm1Y+KWamVAQwg0xM1y0DuGY/YTjh85GsSU\n" +
                "vDfYvlWg5rAvFxhZDQJBAODwJZwZMEH3nja5bP+rDqF5Jzz+2d+FHirpk8hNN56e\n" +
                "Ch4Bl/BjmSvwLaY34WuPwXn/59rwpGLHQal3NZHC5iMCQQDM3TJ8v5/i9SD2bVWP\n" +
                "I3iAaJZYilS9dYjgBryQIoIrbF/9p56JQydcskH1gxgNuFUL3gF6oY6WnRcTcYcx\n" +
                "gpRzAkBEMeplGenchhLsamkWVij4YX+46VbqgjQ0NF5+gpKTPzy3/imvUrLUdFuS\n" +
                "oixwbMPuldwYL47W96v7kc2m5FzbAkBd4yXnPB73caYYqcTjY7svBj+Y9lSdu4bu\n" +
                "PMggOmBzrChV5ILon0kj12IZNWpJp2f6l1UQMYhaiEXyGD6ANzr3AkEAjdAjeWaF\n" +
                "y2k3scCytcw/grCfVkiHFbDi0GEIwJmgcNMBwm50zXNjJ4jWgk5yHNXsnXqIM6wM\n" +
                "U0L32WFNyunenA==\n" +
                "-----END PRIVATE KEY-----"));
        PEMKeyPair pemKeyPair = (PEMKeyPair) pemParser.readObject();
        System.out.println(BouncyCastleProvider.getPublicKey(pemKeyPair.getPublicKeyInfo()));
        System.out.println(BouncyCastleProvider.getPrivateKey(pemKeyPair.getPrivateKeyInfo()));


        return builder.signWith(
                SignatureAlgorithm.ES256,
                BouncyCastleProvider.getPrivateKey(pemKeyPair.getPrivateKeyInfo())).compact();
    }

    public String decodeJwt(String jwtToken) throws Exception {
        PEMParser pemParser = new PEMParser(new StringReader(privateKeyPem));
        PEMKeyPair pemKeyPair = (PEMKeyPair) pemParser.readObject();
        System.out.println(pemKeyPair);
        String publicKey = "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEKOsxJXkG1WgdMT48+mV6roYKCO3w"
                + "i96Wvejk7KqZdGqb2pG/SMaj5Vz6F5X2cg4xxxMNh0B5lEDt2fNQUQm3wA==";
        String pubKeyTest = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC0AbmlX96QZBvWgDLDe3TwwgTU" +
                "+RV9dXslQKhqIRzEjTwcfl4WRSI82E14PiOG5whhJabg0S7+pbGM17bcA7Fq7WQK" +
                "DuIxHFdG4YePEg5vBe1Nb/EtRhrxV2KEJhuXgGCu1EDUTpjtj/a+uhK9jJnLxYNE" +
                "/MiM1YphG6fPuF+duQIDAQAB";
        X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(pubKeyTest));
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(keySpecX509);

        String decodedToken = Jwts.parserBuilder().setSigningKey(pubKey).build().parseClaimsJws(jwtToken).getBody().toString();
        return decodedToken;
    }

    public static void main(String[] args) throws Exception {
        JwtTest jwtTest = new JwtTest();
        String jwtToken = jwtTest.createJwtToken();
        String decodedJwt = jwtTest.decodeJwt(jwtToken);

        System.out.println(decodedJwt);
    }

}

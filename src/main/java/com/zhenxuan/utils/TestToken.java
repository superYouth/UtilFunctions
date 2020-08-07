//package com.zhenxuan.utils;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.JwtBuilder;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
//import org.bouncycastle.jce.provider.BouncyCastleProvider;
//import org.bouncycastle.openssl.PEMKeyPair;
//import org.bouncycastle.openssl.PEMParser;
//import org.junit.Test;
//
//import java.io.StringReader;
//import java.security.PrivateKey;
//import java.security.Provider;
//import java.security.PublicKey;
//import java.security.Security;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.concurrent.TimeUnit;
//
///** Token 测试 */
//public class TestToken {
//  private static String publicKeyPem =
//      "-----BEGIN PUBLIC KEY-----\n"
//          + "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEKOsxJXkG1WgdMT48+mV6roYKCO3w\n"
//          + "i96Wvejk7KqZdGqb2pG/SMaj5Vz6F5X2cg4xxxMNh0B5lEDt2fNQUQm3wA==\n"
//          + "-----END PUBLIC KEY-----\n";
//  private static String privateKeyPem =
//      "-----BEGIN EC PRIVATE KEY-----\n"
//          + "MHcCAQEEIDOB7XrPmVHus3UdK+ovouRMnv1jIx/0iJDfLYDQ3OrJoAoGCCqGSM49\n"
//          + "AwEHoUQDQgAEKOsxJXkG1WgdMT48+mV6roYKCO3wi96Wvejk7KqZdGqb2pG/SMaj\n"
//          + "5Vz6F5X2cg4xxxMNh0B5lEDt2fNQUQm3wA==\n"
//          + "-----END EC PRIVATE KEY-----\n";
//
//  static {
//    Security.addProvider(new BouncyCastleProvider());
//  }
//
//  @Test
//  public void testParsePrivateKeyPem() throws Exception {
//    PEMParser pemParser = new PEMParser(new StringReader(privateKeyPem));
//    PEMKeyPair pemKeyPair = (PEMKeyPair) pemParser.readObject();
//    System.out.println(BouncyCastleProvider.getPublicKey(pemKeyPair.getPublicKeyInfo()));
//    System.out.println(BouncyCastleProvider.getPrivateKey(pemKeyPair.getPrivateKeyInfo()));
//  }
//
//  @Test
//  public void testParsePublicKeyPem() throws Exception {
//    PEMParser pemParser = new PEMParser(new StringReader(publicKeyPem));
//    SubjectPublicKeyInfo subjectPublicKeyInfo = (SubjectPublicKeyInfo) pemParser.readObject();
//    System.out.println(subjectPublicKeyInfo);
//    System.out.println(BouncyCastleProvider.getPublicKey(subjectPublicKeyInfo));
//  }
//
//  @Test
//  public void testIss() throws Exception {
//    PEMParser pemParser = new PEMParser(new StringReader(privateKeyPem));
//    PEMKeyPair pemKeyPair = (PEMKeyPair) pemParser.readObject();
//    PrivateKey key = BouncyCastleProvider.getPrivateKey(pemKeyPair.getPrivateKeyInfo());
//    Date now = new Date();
//    JwtBuilder builder = Jwts.builder();
//    builder.setIssuedAt(now);
//    builder.setExpiration(new Date(now.getTime() + TimeUnit.DAYS.toMillis(30)));
//    builder.claim("iss", "ithaha.cn");
//    builder.claim("aud", "1");
//    builder.claim("sub", "123");
//    builder.claim("name", "");
//    builder.claim("dn", "id=1,OU=User,DC=yitong,DC=com");
//    builder.claim("cn", "ysedutec");
//    builder.claim(
//        "dns",
//        Arrays.asList("id=1,OU=User,DC=dingtalk,DC=com", "id=1,OU=User,DC=crm,DC=ysedutec,DC=com"));
//
//    String token = builder.signWith(SignatureAlgorithm.ES256, key).compact();
//
//    System.out.println(token);
//  }
//
//  @Test
//  public void test() throws Exception {
//    String token =
//        "eyJhbGciOiJFUzI1NiJ9.eyJpYXQiOjE1NjUzMTE4ODIsImV4cCI6MTU2NzkwMzg4MiwiaXNzIjoiaXRjYXN0LmNuIiwiYXVkIjoiMSIsInN1YiI6IjEyMyIsIm5hbWUiOiLkvKDmmbrmkq3lrqIiLCJkbiI6ImlkPTEsT1U9VXNlcixEQz15aXRvbmcsREM9Y29tIiwiY24iOiJ5c2VkdXRlYyIsImRucyI6WyJpZD0xLE9VPVVzZXIsREM9ZGluZ3RhbGssREM9Y29tIiwiaWQ9MSxPVT1Vc2VyLERDPWNybSxEQz15c2VkdXRlYyxEQz1jb20iXX0.9xEQNvFfTODso8imQ-ge3G89K0vgkJI7kU38kYawqi6lEd3Rk-7beZE8o9zYiIVhxuGh5OT7U6T4JiJSV70AZQ";
//
//    PEMParser pemParser = new PEMParser(new StringReader(publicKeyPem));
//    SubjectPublicKeyInfo subjectPublicKeyInfo = (SubjectPublicKeyInfo) pemParser.readObject();
//    PublicKey key = BouncyCastleProvider.getPublicKey(subjectPublicKeyInfo);
//
//    Claims body = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
//    String aud = body.get("aud", String.class);
//    System.out.println(body);
//  }
//
//  @Test
//  public void testSha() {
//    System.out.println(DigestUtils.sha256Hex("aa"));
//  }
//
//  @Test
//  public void testBouncy() {
//    System.out.println("-------列出加密服务提供者-----");
//    Provider[] pro = Security.getProviders();
//    for (Provider p : pro) {
//      System.out.println("Provider:" + p.getName() + " - version:" + p.getVersion());
//      System.out.println(p.getInfo());
//    }
//    System.out.println("-------列出系统支持的消息摘要算法：");
//    for (String s : Security.getAlgorithms("MessageDigest")) {
//      System.out.println(s);
//    }
//    System.out.println("-------列出系统支持的生成公钥和私钥对的算法：");
//    for (String s : Security.getAlgorithms("KeyPairGenerator")) {
//      System.out.println(s);
//    }
//  }
//}

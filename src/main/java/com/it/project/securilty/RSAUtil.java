package com.it.project.securilty;

import org.bouncycastle.asn1.*;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA非对称加密
 * @author fengqigui
 * @Date 2017/12/20 20:15
 */
public class RSAUtil {

    public static final String KEY_ALGORITHM = "RSA";

    /**
     * 公钥字符串
     */
    public static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCyoxgAbN84vnOojZDL/INDGPgt8sikxjzCOGVbQhy8ubQK3g0EDXeb/0l8M3p+b6M7oGUU5jMfJqOBeABk+etERxWYxBp2dq74r9jRLjN/+Avv8ak3tGJM/kj14n8T9JsP6sEtx6/4nDizVyyu3AoUYF0RGa3UDTnL2v6HgHXH3QIDAQAB";

    // 私钥字符串
    public static  String PRIVATE_KEY ;

    /**
     * 读取私钥字符串
     */
    public static void convert() throws Exception {
        Logger logger = LoggerFactory.getLogger(RSAUtil.class);
        byte[] data = null;
        try {
            InputStream is = RSAUtil.class.getResourceAsStream("/enc_pri");
            // 一次读完
           int length = is.available();
            data = new byte[length];
            is.read(data);
            logger.error("读取私钥成功");

        }catch (Exception e){
            logger.error("读取私钥失败", e.getMessage());
        }

        String dataStr = new String(data);
        try {
            PRIVATE_KEY = dataStr;
        }catch (Exception e){
            logger.error("读取私钥失败", e.getMessage());
            throw new RuntimeException(e);
        }

        if (null == PRIVATE_KEY) {
            throw new Exception("fail to private key");
        }

    }

    /**
     * 私钥解密密文
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey (byte[] data) throws Exception {

        convert();
        // 对密钥解密
        byte[] keyBytes = Base64Util.decode(PRIVATE_KEY);
        // 取得私钥
       PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 对数据解密,下面两行等价
        //Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        //Key privateKey = makePrivateKey(PRIVATE_KEY);
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        return cipher.doFinal(data);
    }

    /**
     * 使用公钥加密明文
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, String key) throws Exception {

        // 对公钥解密
        byte[] keyBytes = Base64Util.decode(key);

        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);

        // 对数据加密
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        return cipher.doFinal(data);

    }



    /**
     * 私钥放在服务器，不写死，公约暴露给客户端;
     * 私钥放在：enc_pri
     * 公钥放在：enc_pub
     * @param
     * @throws NoSuchAlgorithmException
     */
    public static void main(String[] args) throws Exception {

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey aPublic = keyPair.getPublic();

        PrivateKey aPrivate = keyPair.getPrivate();
        System.out.println("------------:"+Base64Util.encode(aPublic.getEncoded()));
        System.out.println("------------:"+Base64Util.encode(aPrivate.getEncoded()));

        //RSA加密AES的密钥

        String str = "我在学习Java..";
        System.out.println("source--------:"+str);
        byte[] bytes = encryptByPublicKey(str.getBytes("UTF-8"), PUBLIC_KEY);
        System.out.println("encode--------:"+bytes.toString());
        byte[] bytes1 = decryptByPrivateKey(bytes);
        System.out.println("dncode--------:"+new String(bytes1));
    }


    /**
     * 解决android的问题，android对存在空格的密钥有问题
     * @param stored
     * @return
     * @throws Exception
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    private static Key makePrivateKey(String stored) throws Exception, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] data = Base64Util.decode(stored);
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(new ASN1Integer(0));
        ASN1EncodableVector v2 = new ASN1EncodableVector();
        v2.add(new ASN1ObjectIdentifier(PKCSObjectIdentifiers.rsaEncryption.getId()));
        v2.add(DERNull.INSTANCE);
        v.add(new DERSequence(v2));
        v.add(new DEROctetString(data));
        ASN1Sequence seq = new DERSequence(v);
        byte[] privKey = seq.getEncoded("DER");
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privKey);
        KeyFactory fact = KeyFactory.getInstance("RSA");
        PrivateKey key = fact.generatePrivate(spec);
        return key;
    }

}


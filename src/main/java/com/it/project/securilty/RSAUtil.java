package com.it.project.securilty;

import javax.crypto.Cipher;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.*;
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
    public static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCKKIzWmb1Dm+cY1ypIwz0BvOcI0hZvu+UQVCxNXPlKKQC1ks5YZ5SyUb/NCCXhJ9oW2qOeI93tTBnycz0vgA8OQR8Za6CjLihjSiq43Pdmak8uqo4pzMxkH127YI+bozh3CJAm/fZRCLbvEqbd3DCRAmShOMxi0TmKgDhhaVfW8QIDAQAB";

    // 私钥字符串
    public static  String PRIVATE_KEY ;

    /**
     * 读取私钥字符串
     */
    public static void convert() throws Exception {

        byte[] data = null;
        try {
            InputStream is = RSAUtil.class.getResourceAsStream("/enc_pri");
            // 一次读完
            int length = is.available();
            data = new byte[length];
            is.read(data);
        }catch (Exception e){

        }

        String dataStr = new String(data);
        try {
            PRIVATE_KEY = dataStr;
        }catch (Exception e){}

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
        /*byte[] keyBytes = Base64Util.decode(PRIVATE_KEY);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

        Cipher ci = Cipher.getInstance(keyFactory.getAlgorithm());
        ci.init(Cipher.DECRYPT_MODE, privateKey);
        return ci.doFinal(data);*/
        // 对密钥解密
        byte[] keyBytes = Base64Util.decode(PRIVATE_KEY);

        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
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
        /*byte[] bytes = Base64Util.decode(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(keySpec);
        Cipher ci = Cipher.getInstance(keyFactory.getAlgorithm());
        ci.init(Cipher.ENCRYPT_MODE, publicKey);
        return ci.doFinal(data);*/
        // 对公钥解密
        byte[] keyBytes = Base64Util.decode(key);

        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);

        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        return cipher.doFinal(data);

    }



    /**
     * 私钥放在服务器，不写死，公约暴露给客户端;
     * 私钥放在：enc_pri
     * 公钥放在：enc_pub
     * @param args
     * @throws NoSuchAlgorithmException
     */
    public static void main(String[] args) throws Exception {

        /**
         * AES加密数据
         */
        /*KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey aPublic = keyPair.getPublic();

        PrivateKey aPrivate = keyPair.getPrivate();
        System.out.println("------------:"+Base64Util.encode(aPublic.getEncoded()));
        System.out.println("------------:"+Base64Util.encode(aPrivate.getEncoded()));

        *//*RSA加密AES的密钥*/



        String str = "我在学习Java..";
        System.out.println("source--------:"+str);
        byte[] bytes = encryptByPublicKey(str.getBytes("UTF-8"), PUBLIC_KEY);
        System.out.println("encode--------:"+bytes.toString());
        byte[] bytes1 = decryptByPrivateKey(bytes);
        System.out.println("encode--------:"+new String(bytes1));
    }

}


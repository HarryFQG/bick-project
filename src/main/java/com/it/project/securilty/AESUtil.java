package com.it.project.securilty;

import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

/**
 * 对称加密与解密
 * @author fengqigui
 * @Date 2017/12/20 19:01
 */
public class AESUtil {

    private static final String KEY_ALGORITHM = "AES";
    // 加密的模式
    private static final String KEY_ALGORITHM_MODE = "AES/CBC/PKCS5Padding";

    /**
     * AES加密
     * @param data 明文
     * @param key ：要求位数16位
     * @return
     */
    public static String encrypt (String data, String key) {

        try {
            // 加密的模式
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), KEY_ALGORITHM);
            // 拿到加密算法的类
            Cipher cipher = Cipher.getInstance(KEY_ALGORITHM_MODE);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
            byte[] encrypted = cipher.doFinal(data.getBytes("UTF-8"));
            // 可以使用java提供的BASE64，但是考虑到移动端的加解密问题使用这一个。
            return Base64Util.encode(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


    /**
     * AES解密
     * @param data 密文
     * @param key 要求位数：16位
     * @return
     */
    public static String decrypt (String data, String key) {

        try {
            // 加密的模式
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance(KEY_ALGORITHM_MODE);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
            byte[] originBytes = Base64Util.decode(data);
            byte[] result = cipher.doFinal(originBytes);
            return new String(result,"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


    /**
     * 加密过程：就是AES加密明文的密文，RSA加密AES的密钥，也就是两层加密
     * 解密过程：RSA解密AES的到密钥，AES使用经过解密的密钥解密密文的明文
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String key = "1234567890qwerty";
        String token = "你好我们的同学";
        System.out.println("source:"+token);
        // 使用AES进行加密
        String s = encrypt(token, key);
        System.out.println("encode:"+s);
        // 使用AES进行解密
        String s1 = decrypt(s, key);
        System.out.println("decode:"+s1);

        /*使用RSA的公钥加密AES的密钥*/
        byte[] encrypt = RSAUtil.encryptByPublicKey(key.getBytes("UTF-8"), RSAUtil.PUBLIC_KEY);
        System.out.println("加密之后的AES的密钥："+new String(encrypt,"UTF-8"));
        // 使用AES加密RSA加密AES密钥后的密钥，因为不加密时他是一个乱码，在传输是存在传输问题
        String encode = Base64Util.encode(encrypt);

        /* 服务端RSA解密AES的key*/
        // 先使用AES解密经过AES加密的RSA加密AES密钥，还原为RSA加密AES时的乱码
        byte[] decode = Base64Util.decode(encode);
        // 使用RSA解密经过RSA加密的AES的密钥
        byte[] decrypt = RSAUtil.decryptByPrivateKey(decode);
        // 还原出AES的密钥
        System.out.println(new String(decrypt,"UTF-8"));
        // 使用AES解密密文得到明文
        String s2 = decrypt(s, key);
        System.out.println("decode:"+s2);
    }

}

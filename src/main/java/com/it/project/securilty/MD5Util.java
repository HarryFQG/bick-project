package com.it.project.securilty;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author fengqigui
 * @Date 2017/12/21 21:42
 */
public class MD5Util {

    /**
     * 注意是：commons-codec这个包得
     * @param source 待加密的字符串
     * @return
     */
    public static String getMD5(String source){
        return DigestUtils.md5Hex(source);
    }

}

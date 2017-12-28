package com.it.project.util;



import org.apache.commons.lang3.StringUtils;

import java.util.Random;

/**
 * 产生随机数
 * @author fengqigui
 * @Date 2017/12/28 19:46
 */
public class RandomNumCode {


    public static String verCode(){
        Random random = new Random();
        return StringUtils.substring(String.valueOf(random.nextInt()),2,6);

    }

    public static void main(String[] args) {
        System.out.println("---："+verCode());

    }

}

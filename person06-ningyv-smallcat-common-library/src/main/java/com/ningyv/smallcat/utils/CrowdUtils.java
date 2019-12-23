package com.ningyv.smallcat.utils;


import com.ningyv.smallcat.constant.Constant;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author LCX
 * @create 2019-12-22 15:05
 */
public class CrowdUtils {
    //检查字符串是否有效
    public  static boolean stringEffectCheck(String source){
        return source!=null&&source.trim().length()>0;
    }

    //随机生成验证码
    public static String getRandNum(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append((int) (Math.random()*10));
        }
        return stringBuilder.toString();
    }

    public static void sendRandMessage(String phoneNum,String randRandCode) {
        System.err.println("验证码内容:"+randRandCode);
    }

    //对传入的明文字符串进行加密操作。使用md5算法
    public static String md5(String source) {

        //检查source是否有效
        if (!stringEffectCheck(source)) {
            throw new  RuntimeException(Constant.MSG_STRING_ERROR);
        }

        //声明字符串指定使用的算法名称
        String algorithm="MD5";

        try {
            //获取MessageDigest实例对象
            MessageDigest instance = MessageDigest.getInstance(algorithm);

            //找到明文字符串的	字节数组
            byte[] sourceByteList = source.getBytes();

            //执行加密
            byte[] encodedByteArray = instance.digest(sourceByteList);

            //创建字符数组
            char[] 	charactes = new char[] {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

            StringBuilder stringBuilder = new StringBuilder();

            //遍历encodedByteArray
            for (int i = 0; i < encodedByteArray.length; i++) {

                byte b = encodedByteArray[i];
                //获取当前字节的高四位
                int highValue = (b>>4)&15;
                //获取当前字节的低四位
                int lowValue = b&15;
                //获取高四位对应的字符
                char highChar = charactes[highValue];
                //获取低四位对应的字符
                char lowChar = charactes[lowValue];

                //拼接字符串
                stringBuilder.append(highChar).append(lowChar);

            }
            //返回执行结果
            return stringBuilder.toString();

        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}

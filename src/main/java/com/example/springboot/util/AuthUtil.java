package com.example.springboot.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AuthUtil {

    public static String encodeMd5(String str){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            String md5 = new BigInteger(1, md.digest()).toString(16);
            //BigInteger会把0省略掉，需补全至32位
            return fillMD5(md5);
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return null;
    }

    private static String fillMD5(String md5){
        return md5.length()==32?md5:fillMD5("0"+md5);
    }

    public static boolean checkTimeValid(String timestamp,float second){
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long time = Long.parseLong(timestamp);
        Date now = new Date();

        //float interval = (now.getTime() - date.getTime())/1000;
        long interval = now.getTime() - time;
        float minus = second * 1000; //秒到时间戳的转换
        if(Math.abs(interval)  - minus >0){
            return false;
        }
        return true;
    }
}

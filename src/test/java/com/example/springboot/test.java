package com.example.springboot;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {

    public static void main(String[] args){
        regexTest();
    }

    public static void regexTest(){
        String str = "a(b)(c)(d)";
        Pattern pattern = Pattern.compile(".*?(?=\\()");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()){
            System.out.println(matcher.group());
        };

        str = "aaa";
        pattern = Pattern.compile(".*?");
        matcher = pattern.matcher(str);
        while (matcher.find()){
            System.out.println(matcher.group());
        }

/*        str = "aaa";
        pattern = Pattern.compile("a+?(?=a)");
        matcher = pattern.matcher(str);
        while (matcher.find()){
            System.out.println(matcher.group());
        }*/
/*
        str = "aaabc";
        pattern = Pattern.compile(".*?b");
        matcher = pattern.matcher(str);
        while (matcher.find()){
            System.out.println(matcher.group());
        }

        str = "aingbing";
        pattern = Pattern.compile(".*?(?=ing)");
        matcher = pattern.matcher(str);
        while (matcher.find()){
            System.out.println(matcher.group());
        }*/
    }

    public static void assertTest(){
        int i = 0;

        assert i >1;
    }

    public static void yesterday(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        System.out.println(cal.getTime());

        System.out.println("-----------");

        LocalDateTime today = LocalDateTime.now();
        LocalDateTime yesterday = today.minusDays(1);
        System.out.println(yesterday);
    }

    public static void getMillis(){
        System.out.println(Calendar.getInstance().getTimeInMillis());
        System.out.println(System.currentTimeMillis());
        System.out.println(Clock.systemDefaultZone().millis());
        System.out.println(new Date().getTime());
    }

    public static void timeClassTest(){
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.MONTH)); //0 - 11
        System.out.println(calendar.get(Calendar.DATE));
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println(calendar.get(Calendar.HOUR));
        System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println(calendar.get(Calendar.MINUTE));
        System.out.println(calendar.get(Calendar.MILLISECOND));

        System.out.println("--------");

        LocalDateTime dt = LocalDateTime.now();
        System.out.println(dt.getYear());
        System.out.println(dt.getMonth()); //1 - 11
        System.out.println(dt.getMonthValue());
        System.out.println(dt.getDayOfMonth());
        System.out.println(dt.getHour());
        System.out.println(dt.getMinute());
        System.out.println(dt.getSecond());

    }

    public void stringPlusTest(){
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program";
        String s4 = "ming";
        String s5 = "Program" + "ming";
        String s6 = s3 + s4;
        System.out.println(s1 == s2); //false
        System.out.println(s1 == s5); //true
        System.out.println(s1 == s6); //false
        System.out.println(s1 == s6.intern());  //true
        System.out.println(s2 == s2.intern());  //false

        System.out.println("---------");
        String s7 = "abc";
        String s8 = s7.toString();
        String s9 = s7 + "";

        System.out.println(s7==s8);
        System.out.println(s7==s9);
    }

}

package com.example.springboot;

import org.junit.Test;
import org.omg.CORBA.SystemException;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {

    public static void main(String[] args) throws InterruptedException{

/*        Map<String,Object> map = new HashMap<>(1,1);
        map.put("a","b");
        map.put("d","e");
        map.put("f","g");

        Iterator iterator = map.keySet().iterator();
        ConcurrentSkipListMap concurrentSkipListMap = new ConcurrentSkipListMap();*/
/*        while (iterator.hasNext()){
            //System.out.println(iterator.next());
        }*/

/*        int[] a = {1,2,3,4,5,6};
        System.arraycopy(a,3,a,2,3);
        for (int i : a){
            System.out.println(i);
        }*/
/*
        StringBuffer stringBuffer = new StringBuffer("a");
        StringBuffer stringBuffer1 = stringBuffer;
        StringBuffer stringBuffer2 = new StringBuffer("b");
        stringBuffer = stringBuffer2;
*//*        System.out.println(stringBuffer1);
        System.out.println(stringBuffer);*//*
        System.out.println(new Random(5).nextInt(5));
        ThreadLocalRandom.current().nextInt();
        Random random = new Random();*/

        testFinally();
    }

    public static void testFinally(){
        try {
            System.out.println("try");
        }catch (Exception e){
            System.out.println("exception");
            return;
        }finally {
            System.out.println("finally");
        }
    }

    @Test
    public static void testSyncIfByThread(){
        StringBuffer stringBuffer = new StringBuffer("a");
        StringBuffer stringBuffer1 = new StringBuffer("b");

        ThreadMethodTest threadMethodTest = new ThreadMethodTest(stringBuffer,stringBuffer1);

        ThreadTest threadTest = new ThreadTest(threadMethodTest);
        ThreadTest2 threadTest2  = new ThreadTest2(threadMethodTest);

        new Thread(threadTest).start();
        new Thread(threadTest2).start();
    }

    @Test
    public static void testSyncObjectDiff(){
        Object obj = new Object();
        Object obj2 = new Object();
        ThreadTest threadTest = new ThreadTest(obj);
        ThreadTest2 threadTest2  = new ThreadTest2(obj2);

        new Thread(threadTest).start();
        new Thread(threadTest2).start();
    }

    @Test
    public static void testSyncObject(){
        Object obj = new Object();
        ThreadTest threadTest = new ThreadTest(obj);
        ThreadTest2 threadTest2  = new ThreadTest2(obj);

        new Thread(threadTest).start();
        new Thread(threadTest2).start();
    }

    public static void setIntTest(int i){
        i = 3;
    }

    public static void testCancelTask() throws InterruptedException{
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<?> task = executorService.submit(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while(!Thread.currentThread().isInterrupted()){
                    try {
                        Thread.sleep(1000);
                        System.out.println(i ++);
                    }catch (InterruptedException e){
                        System.out.println("thread sleep interrupt");
                        e.printStackTrace();
                    }
                }
            }
        });

        try {
            task.get(2,TimeUnit.SECONDS);
        }catch (TimeoutException e){
            //e.printStackTrace();
        }catch (ExecutionException e){
            //e.printStackTrace();
        }finally {
            task.cancel(true);
        }

    }

    public static void testThred(){
        StringBuffer stringBuffer = new StringBuffer("a");
        ThreadMethodTest threadMethodTest = new ThreadMethodTest(stringBuffer,null);
        ThreadTest threadTest = new ThreadTest(threadMethodTest);
        ThreadTest2 threadTest2 = new ThreadTest2(threadMethodTest);
        Thread thread1 = new Thread(threadTest);
        Thread thread2 = new Thread(threadTest2);
        thread1.start();
        thread2.start();
    }

    public static void testInteger(){
        Integer s = new Integer(9);
        Integer t = new Integer(9);
        Long u = new Long(9);
        System.out.println(s==t);
        System.out.println(s.equals(t));
        System.out.println(s.equals(u));
    }

    public static void testAB(){
        for (;;);
    }

    public static void testFor(){
        for (;;);
    }

    public static int testsub(){
        int i = 0;
        try {
            i ++;
        }finally {
            i ++;
        }
        return i++;
    }

    public static void testException(){
        try {
            testThrowsException();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void testThrowsException(){
        int i = 1/0;
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

    public void testStringBuffer(){
        StringBuffer sb = new StringBuffer();
        StringBuilder sbd = new StringBuilder();
    }
}

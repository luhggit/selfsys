package com.example.springboot;

public class ThreadTest2 implements Runnable{

    private ThreadMethodTest threadMethodTest;

    public ThreadTest2(ThreadMethodTest threadMethodTest){
        this.threadMethodTest = threadMethodTest;
    }

    private Object obj;

    public ThreadTest2(Object obj){
        this.obj = obj;
    }

    @Override
    public void run(){
        threadMethodTest.printPlus();

/*        Object obj2 = obj;

        synchronized (obj2){
            while (true){
                try {
                    Thread.sleep(1000);
                    System.out.println("thread 2");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
        }*/

/*        StaticTest.i = 3;
        while (true) {
            System.out.println("thread 2 : " + StaticTest.i);
        }*/

/*        try {
            Thread.sleep(1000);
            ThreadLocalTest.threadLocal.set("ThreadTest2");
            Thread.sleep(1000);
            System.out.println(ThreadLocalTest.threadLocal.get());
        }catch (InterruptedException e){
            e.printStackTrace();
        }*/
        /*while(true){
            threadMethodTest.printMinus();
        }*/
    }
}

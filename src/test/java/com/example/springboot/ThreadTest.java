package com.example.springboot;

public class ThreadTest implements Runnable{

    private ThreadMethodTest threadMethodTest;

    public ThreadTest(ThreadMethodTest threadMethodTest){
        this.threadMethodTest = threadMethodTest;
    }

    private Object obj;

    public ThreadTest(Object obj){
        this.obj = obj;
    }

    @Override
    public void run(){
        threadMethodTest.printMinus();


/*        synchronized (obj){
            while (true){
                try {
                    Thread.sleep(1000);
                    System.out.println("thread 1");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
        }*/

/*        StaticTest.i = 2;
        while (true) {
            System.out.println("thread 1 : " + StaticTest.i);
        }*/

/*        ThreadLocalTest.threadLocal.set("ThreadTest");
        try {
            Thread.sleep(3000);
            System.out.println(ThreadLocalTest.threadLocal.get());
        }catch (InterruptedException e){
            e.printStackTrace();
        }*/

        //threadMethodTest.printPlus();
    }

}

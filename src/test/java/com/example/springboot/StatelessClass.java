package com.example.springboot;


public class StatelessClass implements Runnable{

    public int get(){
        return StaticClassTest.addAndGet();
    }

    @Override
    public void run() {
        while (true){
            try{
                Thread.sleep(1000);
                int i = get();
                System.out.println(i);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new StatelessClass()).start();
    }
}
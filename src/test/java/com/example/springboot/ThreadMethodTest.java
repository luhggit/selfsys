package com.example.springboot;

import java.util.Vector;

public class ThreadMethodTest {

    private StringBuffer sb;

    private StringBuffer sb2;

    private Vector vector;

    public ThreadMethodTest(StringBuffer sb,StringBuffer sb2){
        this.sb = sb;
        this.sb2 = sb2;
    }

    public  void printMinus(){
        synchronized (sb){
            while(true) {
                System.out.println("--------------------------");
            }
        }
    }

    public  void printPlus(){
        synchronized (sb2){
            while(true) {
                System.out.println("++++++++++++++++++++++++++");
            }
        }
    }

    public static void printPlusVector(Vector list){
        synchronized (list){
            while (true){

            }
        }
    }
}

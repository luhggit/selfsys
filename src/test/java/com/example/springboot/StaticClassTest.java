package com.example.springboot;

public class StaticClassTest{
    private static int count = 0;

    public static int addAndGet(){
        count ++;
        return count;
    }
}
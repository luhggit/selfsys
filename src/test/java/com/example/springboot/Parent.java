package com.example.springboot;

public class Parent {
    public  String name = "parent name";

    public String getName(){
        return this.name;
    }

    public Parent(){
        System.out.println("parent construct");
    }

    public Parent(int i){

    }
}

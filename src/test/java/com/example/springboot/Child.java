package com.example.springboot;

public class Child extends Parent {
    private static int x;

    public  String name = "child name";

    public Child(){
        super(1);
        System.out.println("child consturct");
    }

    public static void main(String[] args){
        Parent child = new Child();
        System.out.println(child.getName());
    }
}

package com.example.springboot;

public class Tree {
    private static String tree = "tree";

    String getTree(){
        return tree;
    }

    public static class Elm extends Tree{
        private static String tree = "elm";
        public static void main(String[] args){
            new Elm().go(new Tree());
        }

        void go(Tree t){
            String s = t.getTree() + Elm.tree + tree +  (new Elm().getTree());
            System.out.println(s);
        }
    }
}

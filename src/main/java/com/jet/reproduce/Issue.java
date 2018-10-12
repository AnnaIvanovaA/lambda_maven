package com.jet.reproduce;

public class Issue {
    public static void main(String[] args) {
        foo();
        foobar();
    }

    public static void foo(){
        bar();
    }

    public static void bar(){
        foo();
    }
    public static void foobar(){
        foo();
    }
}

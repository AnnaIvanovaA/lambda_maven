package com.jet.Stepping;

public class SmartStepIntoCheck {

    public static void main(String[] args) {
        (new SmartStepIntoCheck()).check(foo() + boo() + boo() + foo() + foo() + foo("qq") + boo(5) + foo() + boo());
    }

    public void check(String str){
        System.out.println(str);
    }

    public static String foo(){
        return "foo";
    }

    public static String foo(String s){
        System.out.println(s);
        return s;
    }

    public static String boo(){
        return "boo";
    }

    public static String boo(int i){
        return Integer.toString(i);
    }
}

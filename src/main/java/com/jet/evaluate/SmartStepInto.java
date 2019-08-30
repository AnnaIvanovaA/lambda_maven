package com.jet.evaluate;

/**
 * Shift + F7 on the line where BP was set
 */

public class SmartStepInto {
    //breakpoint for main method
    public static void main(String[] args) {

        //case 1
        check(foo() //<BP> here
                    + boo());
        String str = foo() //<BP> here
                + boo();
    }

    public static void bar(Runnable r) {
        r.run();
    }


    public static String foo(){
        return "foo string";
    }

    public static String boo(){
        return "boo string";
    }

    public static void check(String str){
        System.out.println(str);
    }
}

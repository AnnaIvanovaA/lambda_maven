package com.jet.Stepping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SmartStepInto {
    //for several method calls, it's possible to choose in which one to step

    public static void main(String[] args) throws IOException { //BP here for lambda
        //lambda
        bar(() -> System.out.println("Hello"));


        //simple
        (new SmartStepInto()).check(foo() // with a breakpoint on this line
                + boo()
                + foo("qq")
                + boo(5));

        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in)); //BP
        String line;
        while (!((line = rd.readLine()).equals("exit"))){
            System.out.println(line);
        }

    }

    public void check(String str){
        System.out.println(str);
    }


    public static String foo(){
        return "foo";
    }

    public static String foo(String s){
        return s;
    }

    public static String boo(){
        return "boo";
    }

    public static String boo(int i){
        return Integer.toString(i);
    }

    public static void bar(Runnable r) {
        r.run();
    }
}

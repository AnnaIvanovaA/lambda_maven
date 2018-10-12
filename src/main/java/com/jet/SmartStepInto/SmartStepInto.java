package com.jet.SmartStepInto;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class SmartStepInto {

    public static void main(String[] args) { //BP here for lambda
        //lambda
        bar(() -> System.out.println("Hello"));

        //simple
        (new SmartStepInto()).check(foo() // with a breakpoint on this line
                + boo());
    }

    public void check(String str){
        System.out.println("smth");
    }

    @NotNull
    @Contract(pure = true)
    public static String foo(){
        return "foo";
    }
    @NotNull
    @Contract(pure = true)
    public static String boo(){
        return "boo";
    }

    public static void bar(Runnable r) {
        r.run();
    }
}

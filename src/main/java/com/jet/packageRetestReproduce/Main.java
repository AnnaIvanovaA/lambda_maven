package com.jet.packageRetestReproduce;

import com.jet.breakpoints.MyLambda;
import org.jetbrains.annotations.NotNull;

public class Main {

    private static String var;


    public static void main(String[] args) {

        testMethod(null);
        testMethod(6);
        voidMethod(null);
        voidMethod(8);

        String z = "1234512345123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345";
        String[] x = new String[]{z,z,z,z,z,z,z,z,z,z,z,z,z,z,z,z,z,z,z,z,z,z};
        System.out.println(x);
    }


    public static int testMethod(Integer value) {
        System.out.println("do smth");
        return value + 5;
    }

    public static void voidMethod(Integer value) {
        Main.var = Integer.toString(value);
        Main.var = Integer.toString(value+3);
    }
}

package com.jet.breakpoints;

import java.util.List;
import java.util.stream.Collectors;

public class MyClass {
    static String str;

    public static void main(String[] args) {
        str = getSomeString();

        try {
            User user2 = null;
            user2.getAge();
        }
        catch (Exception e){

        }
    }

    public static String getSomeString(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "some string";
    }
}

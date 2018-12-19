package com.jet.breakpoints;

public class MyClassName {
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

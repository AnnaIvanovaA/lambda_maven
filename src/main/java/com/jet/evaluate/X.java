package com.jet.evaluate;

public class X {
    X x = this;
    String s = "ok";

    public static void main(String... args) {
        X x = new X();
        String str = "abc";
        int l = str.length();
        int k = 1000 + 200 + 22 + 2;

        System.out.println(x.x.x.x.s);
        System.out.println("");
    }
}

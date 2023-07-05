package com.jet.evaluate;

public class BigStringFreeze {
    public static void main(String[] args) {
        StringBuffer a = new StringBuffer();
        for(int i=0; i< 100_000_000;i++){
            a.append("foobar");
        }
        System.out.println("foo"); //breakpoint here + show content of variable a
    }
}

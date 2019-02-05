package com.jet.kotlin;

public class JavaClass {
    public static void main(String[] args) {

        int x = 0;
        long t = System.currentTimeMillis();
        System.out.println(t);

        int x1= x + 1;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int x2= x + 1;
        int x3= x + 1;
        int x4= x + 1;
        int x5= x + 1;
        int x6= x + 1;
        int x7= x + 1;
        int x8= x + 1;
        int x9= x + 1;
        int x10= x + 1;
        int x11= x + 1;

        System.out.println(System.currentTimeMillis() - t);
    }
}

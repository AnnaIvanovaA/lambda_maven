package com.jet.performance;

public class HintsCheck {

    public static void main(String[] args) throws InterruptedException {
        System.out.println();
        Thread.sleep(150);
        Thread.sleep(1000);
        Thread.sleep(1000);
        Thread.sleep(100);
        System.out.println();

        B.test();
        B.test2();
        B.test();
        B.test2();
        System.out.println();
    }

    class B {
        static void test(){
            for (int i = 0; i < 10000; i++) {
                System.out.println(i);
            }
        }
        static void test2(){
            for (int i = 0; i < 1000000; i++) {
                System.out.println(i);
            }
        }

    }
}

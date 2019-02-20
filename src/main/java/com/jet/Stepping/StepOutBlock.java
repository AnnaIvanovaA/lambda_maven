package com.jet.Stepping;

public class StepOutBlock {

    public static void main(String[] args) {
        fooo();
        int[] arr = {2, 0, 1, 3};
        for (int el : arr) {
            System.out.println(el);
        }
        System.out.println();

    }

    public static void fooo() {
        System.out.println("some action where I start stepping");
        for (int x=0;  x<1000; x++) {
            System.out.println("unrelated action. I wanna skip it");
        }
        System.out.println("The most important action that I want to debug");
    }



}

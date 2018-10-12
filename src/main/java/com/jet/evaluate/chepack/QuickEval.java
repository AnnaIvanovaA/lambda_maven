package com.jet.evaluate.chepack;

public class QuickEval {
    public static void main(String[] args) {
        //debug, alt+click to quick evaluate
        ((Runnable) System.out::println).run();
        ((Runnable) () -> System.out.println(25)).run();
    }
}

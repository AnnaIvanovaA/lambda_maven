package com.jet.evaluate;

public class CompilingEvaluator {
    public static void main(String[] args) {
        final class Inner {
        }
        Inner i = new Inner();
        //BP on the next line
        ((Runnable)(() -> System.out.println(i.hashCode()))).run();
    }
}

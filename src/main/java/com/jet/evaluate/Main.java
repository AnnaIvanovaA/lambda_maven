package com.jet.evaluate;


import java.util.function.Supplier;

public class Main {
    public static <T> T wrap(Supplier<T> supplier) {
        return supplier.get();
    }

    public static class Subclass {
    }

    public static Object[] create() {
        return new Subclass[]{};
    }


    public static void main(String[] args) {

        wrap(() -> create());

        Evaluate evaluate = new Evaluate();
        evaluate.getName();

        int[] anArray = {1, 2, 3};
        //System.out.println(anArray[5]);
        System.out.println("OK");


    }
}

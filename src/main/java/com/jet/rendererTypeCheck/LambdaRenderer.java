package com.jet.rendererTypeCheck;

import java.util.List;
import java.util.function.Supplier;

public class LambdaRenderer {

    public static void main(String[] args) {
        List<Supplier<Object>> list = List.of(
                number(1),
                number(2),
                string(""),
                string("string"),
                () -> true
        );
        // Breakpoint!
        System.out.println(list);
    }

    private static Supplier<Object> number(int n) {
        return () -> n;
    }

    private static Supplier<Object> string(String s) {
        return () -> s;
    }
}


package com.jet.breakpoints;

public class Instrumentation {
    public static void main(String[] args) {
        f1("a");
        f2("a");
    }

    static void f1(String k) {
        String out = switch (k) { case "a" -> "x"; default -> "y"; };
//        if (k.equals("a")) {
//            out = "x";
//        } else {
//            out = "y";
//        }

        String s = "s";
        String t = "t";
        System.out.println(out + t + s ); // ← breakpoint, condition: `s.equals("s")`
    }

    static void f2(String k) {
        int r;
        switch (k) { case "a": r = 1; break; default: r = 2; }
        int i = 5;
        int j = 7;
        System.out.println(r + i + j); // ← breakpoint, condition: `i == 5`
    }
}

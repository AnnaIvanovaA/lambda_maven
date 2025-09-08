package com.jet.reproduce;

public class LabelsCheck {
    public static void main(String[] args) {
        foo("abc");
        foo("def");
        foo("ghi");
    }

    public static void foo(String s) {
        System.out.println(s.length()); // <- breakpoint here, Mark object - label X for s
    }
}

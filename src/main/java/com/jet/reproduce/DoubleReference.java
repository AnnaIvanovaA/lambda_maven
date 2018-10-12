package com.jet.reproduce;

import java.util.HashMap;

public class DoubleReference {
    public static void main(String[] argv) {
        DoubleReferred referred = new DoubleReferred();
        DoubleReference reference = new DoubleReference(referred);
        System.out.println(reference); // Breakpoint here.

        HashMap<String, String> newMap = new HashMap<>();
        String value = "someValue";
        String str1 = value +"1";
        String str2 = value + "we";
        newMap.put(value, value);
        newMap.put(str1, str1);
        newMap.put(str1, str2);
        System.out.println();
    }

    private DoubleReferred field1;
    private DoubleReferred field2;
    public DoubleReference(DoubleReferred referred) {
        field1 = referred;
        field2 = referred;
    }
    static class DoubleReferred {};
}

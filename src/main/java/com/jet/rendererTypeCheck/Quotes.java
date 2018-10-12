package com.jet.rendererTypeCheck;

import java.util.HashMap;

public class Quotes {
    public static void main(String[] args) {
        char[] arr = new char[20];
        arr = "char".toCharArray();

        int a = arr.length;

        HashMap<char[], String> map = new HashMap<>();
        map.put(arr, "Smth");
        map.put("char2".toCharArray(), "Smth");

    }
}

package com.jet.exceptions;

public class NPECheck {
    public static void main(String[] args) {
        String text = null;
        System.out.println(text.length()); // NPE
    }
}

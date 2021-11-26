package com.jet.terminal;

public class OutputCheck {
    public static void main(String[] args) throws Exception {
        System.err.println("stderr");
        throw new Exception("example");
    }
}

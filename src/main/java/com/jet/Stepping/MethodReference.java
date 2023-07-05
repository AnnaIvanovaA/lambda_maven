package com.jet.Stepping;

public class MethodReference {

    public static void main(String[] args) {
        MethodReference breaks = new MethodReference();
        new Thread(() -> breaks.somethingSmart()).start(); // Line A.
        new Thread(breaks::somethingSmart).start(); // Line B.
    }
    public void somethingSmart() { System.out.println("smart"); }
}

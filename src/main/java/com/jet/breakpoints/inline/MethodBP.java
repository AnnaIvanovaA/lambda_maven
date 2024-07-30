package com.jet.breakpoints.inline;

public class MethodBP {

    static abstract class Base{
        abstract void foo();
    }

    static class Impl1 extends Base{
        @Override
        void foo() {
            System.out.println("Smth");
        }
    }

    public static void main(String[] args) {
        new Impl1().foo();
        System.out.println();
    }

}

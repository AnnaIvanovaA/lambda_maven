package com.jet.SmartStepInto;

public class SmartStepAlreadyExecuted2 {
    public static void main(String[] args) {
        new A3().main();
    }

    static class A1 {
        int foo() {
            return 1;
        }
    }

    static class A2 extends A1 {
    }

    static class A3 extends A2 {
        public int main() {
            System.out.println("foo");
            //Breakpoint!
            return foo();
        }
    }
}

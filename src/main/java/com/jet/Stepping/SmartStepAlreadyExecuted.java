package com.jet.Stepping;

public class SmartStepAlreadyExecuted {
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
            return foo() + String.valueOf(11).length(); // step into foo, then step over, then do smart step into, it should not contain foo any more
        }
    }
}

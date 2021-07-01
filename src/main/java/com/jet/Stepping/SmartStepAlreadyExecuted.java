package com.jet.Stepping;

public class SmartStepAlreadyExecuted {



    public static void main(String[] args) {
        new A3().main();
    }

    static class A1 {
//        int foo() {
//            return 1;
//        }
        B b;
        private int nameId;
        private int nameHash;

        int foo() {
            B b = new B();
            A1 a3 = new A1();
            a3.b = b;

            nameId = 16;
            nameHash = nameId + 230123;
            System.out.println("ok"); //breakpoint
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

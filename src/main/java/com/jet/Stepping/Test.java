package com.jet.Stepping;

import kotlin.jvm.functions.Function0;

public class Test {
    public static <T> void test(Function0<Integer> f1) {
        f1.invoke(); // Try smart step into here
    }

    public static void main(String[] args) {
        test(Test::mr);

        X x = new X();
        x.foo().bar();  //set breakpoint here
    }

    public static int mr() {
        return 12;
    }
}

class X {
    Y foo() {
        return new Y();
    }
}
class Y {
    void bar(){}
}
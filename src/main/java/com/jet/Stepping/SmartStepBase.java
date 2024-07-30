package com.jet.Stepping;

public class SmartStepBase {

    public static void main(String[] args) {
        Impl1 i1 = new Impl1();
        Impl2 i2 = new Impl2();
        var iii = i1.foo() + i2.foo();
    }

}

class Base {
    public int foo() {
        return 0;
    }
}

class Impl1 extends Base {}
class Impl2 extends Base {}

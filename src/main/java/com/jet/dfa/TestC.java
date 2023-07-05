package com.jet.dfa;

public class TestC {
    public static void main(String[] args) {
        new TestC().alwaysTrue();
        boolean bool = new TestC().member;
        new TestC().f();

    }

        boolean alwaysTrue() {
            return true;
        }

        void f() {
            if (!alwaysTrue()) {
                return;
            }
        }
        boolean member = !alwaysTrue();
}

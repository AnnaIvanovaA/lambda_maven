package com.jet.rendererTypeCheck;

public class InlineValues {
    static class A {
        private final int field; // field: 10

        A(int field) {
            this.field = field;
        }

        public void doFooo(final A obj) { //obj: Main$A@451
            // stop here
            // obj.field = 3, but inline debugger shows field:10 which is confusing
            if (obj.field == 10) { //obj: Main$A@451 field: 10
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        final A a = new A(10);
        final A b = new A(3);

        a.doFooo(b);
    }
}

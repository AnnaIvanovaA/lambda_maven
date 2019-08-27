package com.jet.Stepping;

public class Conditions {
    public static void main(String[] args) {
        Integer left = 1;
        Integer right = null;

        if (isNull(left) && isNotNull(right) || isNull(right) && isNotNull(left)){
            System.out.println("smth");
        }


        Inner.foo(2);
        //Breakpoint!
        if ((Inner.foo(1) & Inner.boo(1)) || (Inner.foof(2) & Inner.boof(2))) {
            System.out.println("xxx");
        }
        System.out.println("done");
    }
    public static boolean isNull(Integer i){

        return i == null;
    }

    public static boolean isNotNull(Integer i){
        return i != null;
    }

    static class Inner {
        private static boolean boo(int i) {
            return false;
        }

        private static boolean foo(int i) {
            return false;
        }

        private static boolean foof(int i) {
            return false;
        }

        private static boolean boof(int i) {
            return false;
        }

    }
}

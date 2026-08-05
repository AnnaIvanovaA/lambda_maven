package com.jet.hotswap;

public class HotSwapRepro {

    private static int foo(){
        var sum = 0;
        for (int i = 0; i < 6; i++) {
            var x = i *10;
            sum += x;
        }
        return sum;
    }

    public static void main(String[] args) {
        var res1 = foo();
        System.out.println("res1 " + res1);
        var res2 = foo();
        System.out.println("res2 " + res2);
        var res3 = foo();
        System.out.println("res3 " + res3);
    }
}

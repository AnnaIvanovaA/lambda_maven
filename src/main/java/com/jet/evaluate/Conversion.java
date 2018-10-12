package com.jet.evaluate;

public class Conversion {
    public static void main(String[] args) {

        /*
        1
        widening primitive conversion
        byte to short, int, long, float, or double : (byte) 256
        -128 .. 127

        short to int, long, float, or double : (short) 33000
        -32 768 .. 32767

        char to int, long, float, or double : (char) 'a'
        16bit min (0) '\u0000'; max (65535) '\uffff';

        int to long, float, or double : (int) 2 147 483 647
        -2^31 .. 2^31-1


        long to float or double
        -2^63 .. 2^63-1

        float to double

        */
        int iVar = 2147483647;
        float floatInt = (float) iVar + 10;
        int intFloat = (int) floatInt;

        long longVar = 2147483649L;
        float floatVar = 2147483649f;
        double doubleVar = 2147483649.0;


        int big = 1234567890;
        float approx = big;
        System.out.println(big - (int)approx);

        char charVar = 'a';
        floatVar = (float) charVar;
        doubleVar = (double) charVar;


        // Casting conversion (5.4) of a float literal to
        // type int. Without the cast operator, this would
        // be a compile-time error, because this is a
        // narrowing conversion (5.1.3):
        int i = (int)12.5f;
        // String conversion (5.4) of i's int value:
        System.out.println("(int)12.5f==" + i);

        // Assignment conversion (5.2) of i's value to type
        // float. This is a widening conversion (5.1.2):
        float f = i;
        // String conversion of f's float value:
        System.out.println("after float widening: " + f);

        // Numeric promotion (5.6) of i's value to type
        // float. This is a binary numeric promotion.
        // After promotion, the operation is float*float:
        System.out.print(f);
        f = f * i;
        // Two string conversions of i and f:
        System.out.println("*" + i + "==" + f);

        // Method invocation conversion (5.3) of f's value
        // to type double, needed because the method Math.sin
        // accepts only a double argument:
        double d = Math.sin(f);
        // Two string conversions of f and d:
        System.out.println("Math.sin(" + f + ")==" + d);



        //bug retest
        double a = Integer.MAX_VALUE * 1.5;
        int b = (int) a;
        System.out.println("b = " + b + " MAX = " + Integer.MAX_VALUE);
        boolean flag = (int) (Integer.MAX_VALUE * 1.5) == Integer.MAX_VALUE;



        //new bug
        int q = 12;
        float w = q;
        System.out.println("after float widening: " + w);

        int q2 = (int) 1.678E3f;
        float w2 = q2;
        System.out.println("after float widening 2: " + w2);

        int q3 = (int) 12.5f;
        float w3 = q3;
        System.out.println("after float widening 2: " + w3);


        System.out.println("that's all");
    }
}

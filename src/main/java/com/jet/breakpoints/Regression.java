package com.jet.breakpoints;

public class Regression {

    //stop on breakpoint in finally block
    public static void main(String[] args)
    {
        try
        {
            System.out.println("ATest.main 1");
            doThis();
            System.out.println("ATest.main 2");
        }
        finally
        {
            System.out.println("ATest.main finally");
        }
    }

    private static void doThis()
    {
        throw new RuntimeException();
    }
}

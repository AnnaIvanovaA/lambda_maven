package com.jet.breakpoints;

public class InstrumentedConditionInInnerClasses
{
    public static void main(String[] args)
    {
        StaticInner.run(args.length);
        new InstrumentedConditionInInnerClasses().new Inner().run(args.length);
    }

    static class StaticInner
    {
        static void run(int value)
        {
            //Breakpoint! Condition(value >= 0)
            System.out.println("static");
        }
    }

    class Inner
    {
        void run(int value)
        {
            //Breakpoint! Condition(value >= 0)
            System.out.println("non-static");
        }
    }
}


package com.jet.variableView;

public class Child1 extends ParentClass{
    public Child1(String str) {
        super(str);
        for (int i = 0; i < 10; i++) {
            System.out.println("ch1 created");
        }
        System.out.println();

    }
}

package com.jet.variableView;

public class ParentClass {
    String str;

    public ParentClass(String str) {
        this.str = str;
        for (int i = 0; i < 10; i++) {
            System.out.println("parent method");
        }
        System.out.println();

    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}

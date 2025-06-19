package com.jet.breakpoints;

public class Class_2018_2 {
    int var1;
    String str = "smth";

    public static void main(String[] args) {
        Class_2018_2 cl = new Class_2018_2();
        cl.setStr("Str");
        cl.getStr();

        cl.setVar1(24);
        cl.getVar1();

    }

    public int getVar1() {
        return var1;
    }

    public void setVar1(int var1) {
        this.var1 = var1;
    }

    public void foo(int var1, int var2) {
        this.var1 = var1;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}

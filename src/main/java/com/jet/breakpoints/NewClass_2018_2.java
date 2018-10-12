package com.jet.breakpoints;

public class NewClass_2018_2 {


    int var1;
    String str = "smth";

    public static void main(String[] args) {
        Class_2018_2 cl = new Class_2018_2();
        cl.setStr("Str");
        cl.setVar1(24);
        cl.getVar1();
        cl.getStr();

    }

    public int getVar1() {
        return var1;
    }

    public void setVar1(int var1) {
        this.var1 = var1;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}


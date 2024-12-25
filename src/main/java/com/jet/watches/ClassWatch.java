package com.jet.watches;

public class ClassWatch {
    public static void main(String[] args) throws InterruptedException {

        "abc".toString(); // set a breakpoint here and then do step over to see a Show Method return values
        //Show method return values is shown only on step out or step over
        upper("hello"); // Step over here or step out from the method

        //Thread.sleep(100 * 1000);
        MyClass obj1 = new MyClass("Object 1", 10);
        MyClass obj2 = new MyClass("Object 2", 20);

        obj1.setValue(15); // This will trigger the watchpoint
        obj2.setValue(25); // This will also trigger the watchpoint

        System.out.println("obj1: " + obj1.getName() + ", value: " + obj1.getValue());
        System.out.println("obj2: " + obj2.getName() + ", value: " + obj2.getValue());
    }

    private static String upper(String s) {
        return s.toUpperCase();
    }
}

class MyClass {
    private String name;
    private int value;

    public MyClass(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

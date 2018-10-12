package com.jet.packageRetestReproduce;

public class ComponentTest {
    public static void main(String[] args) {


        new Component().getSomeStringMethod();

        Component component = new Component();
        component.setName("comp");
        component.getName().toUpperCase();



    }

    public static String getInstance(Component component){
        return component.getName();
    }
}

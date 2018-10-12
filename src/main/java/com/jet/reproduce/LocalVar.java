package com.jet.reproduce;

public class LocalVar {

    private String label;

    public LocalVar() {
        this.label = "c";
    }

    public void setAnotherLabel(String label){
        System.out.println("smth");
        label.getBytes();
        label.hashCode();
    }
}

package com.jet.referring;

class Man {

    String hairColor;
    int height;
    double salary;
    String name;

    public Man()
    {
        hairColor = "brown";
        height = 180;
        salary = 50500.5;
        name = "John";
    }
    public Man(String hair, int high, double pay, String name)
    {
        this.height = high;
        this.hairColor = hair;
        this.salary = pay;
        this.name = name;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
package com.jet.intentions;

public class ExcludeCallerClass {

    public static void main(String[] args) {

        User user = new User(25, "John");
        System.out.println(user.getAge());

    }
}

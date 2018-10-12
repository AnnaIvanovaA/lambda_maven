package com.jet.reproduce;


public class Factorial_Recursion {

    public static void main(String[] args) {
        System.out.println(new Factorial_Recursion().factorial(5));

    }

    public int factorial(int n){
        if (n == 0) return 1;
        return n * factorial(n-1);
    }
}

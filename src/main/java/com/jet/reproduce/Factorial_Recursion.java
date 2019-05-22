package com.jet.reproduce;


public class Factorial_Recursion {

    public static void main(String[] args) {
        System.out.println(new Factorial_Recursion().factorial(5));
        System.out.println(new Factorial_Recursion().sum(14));
    }

    private int factorial(int n){
        if (n == 0) return 1;
        return n * factorial(n-1);
    }

    private int sum(int n) {
        if (n >= 1) {
            return sum(n - 1) + n;
        }
        return n;
    }
}

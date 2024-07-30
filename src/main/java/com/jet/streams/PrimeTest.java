package com.jet.streams;

import java.util.stream.IntStream;

public class PrimeTest {

    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }

        return IntStream.rangeClosed(2, (int) Math.sqrt(num)).noneMatch(i -> num % i == 0);
    }
}
package com.jet.Stepping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MapFilterCollect {
    public static void main(String[] args) {
        List<String> numbers = Arrays.asList("1", "2", "3", "4", "5", "6");
        System.out.println("original list: " + numbers);

        List<Integer> even = numbers.stream()
                .map(s -> Integer.valueOf(s))
                .filter(number -> number % 2 == 0)
                .map(k -> Integer.valueOf(k)*2)
                .filter(value -> value > 10)
                .collect(Collectors.toList());

        System.out.println("processed list, only even numbers: " + even);


    }
}

package com.jet.streams;

import java.util.List;
import java.util.stream.Collectors;


public class NumberStream {
    private static final double PRICE=Math.max(1.0, 2.0);
    final double[] dPoint = new double[10];

    public static void main(String[] args) throws InterruptedException {

        System.out.println("fdsfsdf");
        Thread.sleep(3000);
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        //final double[] dPoint = new double[numbers.size()];
        String invertedOddNumbers = numbers
                .stream()
                .filter(it -> it % 2 != 0).map(it -> -it)
                .map(Object::toString)
                .collect(Collectors.joining("; "));
        System.out.println(invertedOddNumbers);
    }
            }
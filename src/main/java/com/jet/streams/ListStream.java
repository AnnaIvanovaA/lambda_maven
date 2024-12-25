package com.jet.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ListStream {
    public static void main(String[] args) throws InterruptedException {

        List.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20).stream()
                .filter(i -> i % 2 == 0)
                .limit(19)
                .toList();

        Thread.sleep(100);
        IntStream.iterate(1, n -> n + 1)
                .skip(Integer.parseInt(args[0]))
                .limit(Integer.parseInt(args[1]))
                .filter(PrimeTest::isPrime)
                .forEach(System.out::println);

        List<String[]> arr = new ArrayList<>();
        arr.add(new String[]{"1","2"});

        arr.add(new String[]{"2","3"});
        arr.add(new String[]{"3","4"});
        arr.add(new String[]{"4","5"});
        arr.add(new String[]{"8","9"});

 arr.add(new String[]{"8","9"});
  arr.add(new String[]{"8","9"});
arr.add(new String[]{"8","9"});
        List<String> collect = arr.stream().flatMap(a -> Stream.of(a)).distinct().collect(Collectors.toList());
        System.out.format("collector: %s", collect).println();


        List<String> collect1 = arr.stream().flatMap(Stream::of).distinct().collect(Collectors.toList());
        System.out.format("collector1vx: %s", arr.stream().flatMap(Stream::of).distinct().collect(Collectors.toList())).println();
        System.out.format("collector1: %s", collect1).println();
        List<String> collect2 = arr.stream().flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        System.out.format("collector2: %s", collect2).println();


        for (int i = 0; i < 3; i++) {
            System.out.println(i);
        }

    }
}
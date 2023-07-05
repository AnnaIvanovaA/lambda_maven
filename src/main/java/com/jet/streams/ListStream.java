package com.jet.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListStream {
    public static void main(String[] args) {
        List<String[]> arr = new ArrayList<>();
        arr.add(new String[]{"1","2"});
        arr.add(new String[]{"2","3"});
        arr.add(new String[]{"3","4"});
        arr.add(new String[]{"4","5"});

        List<String> collect = arr.stream().flatMap(a -> Stream.of(a)).distinct().collect(Collectors.toList());
        System.out.format("collector: %s", collect).println();
        List<String> collect1 = arr.stream().flatMap(Stream::of).distinct().collect(Collectors.toList());
        System.out.format("collector1vx: %s", arr.stream().flatMap(Stream::of).distinct().collect(Collectors.toList())).println();
        System.out.format("collector1: %s", collect1).println();
        List<String> collect2 = arr.stream().flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        System.out.format("collector2: %s", collect2).println();
    }
}

package com.jet.streams;

import java.util.stream.Stream;

public class ToListStream {
    public static void main(String[] args) throws InterruptedException {
        var list1 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .map (a -> 2* a)
                .toList();

        Thread.sleep(5000);
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .filter(i -> i % 2 == 0)
                .toArray();

        Stream.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21)
                .filter(i -> {
                    return i % 2 == 0;
                }).toList();


//        Java 22 preview
//        var list = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
//                .gather(Gatherers.mapConcurrent(1, x -> x * x))
//                .toList();
    }
}

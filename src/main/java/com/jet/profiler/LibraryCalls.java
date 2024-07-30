package com.jet.profiler;

import java.util.stream.IntStream;

public class LibraryCalls {
    public static void main(String[] args) throws InterruptedException {
        var l = IntStream.iterate(1, (k) -> k + 1)
                .takeWhile((k) -> k < 30000000)
                .boxed()
                .toList();
        System.out.println(l);

        var j = IntStream.iterate(1, (k) -> k + 1)
                .takeWhile((k) -> k < 30000000)
                .boxed()
                .toList();
        System.out.println(j);
    }
}

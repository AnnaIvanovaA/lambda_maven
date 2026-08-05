package com.jet.breakpoints;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LoggingBP {
    //TODO smth

    public static void main(String[] args) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
       // Thread.sleep(1000);
        int count = 0;
        for (int i = 0; i < 5; i++) {
            if (isInterested(random.nextInt(10))) {
                count++;
            }

        }
        System.out.println("Found %d interested values%n" + count);
        System.out.println("qwer");
        System.out.println(count);
        System.out.printf("Found %d interested values%n", count);
        System.out.println("");
        System.out.println();
        System.out.println();

        List<Integer> numbers = IntStream.rangeClosed(1, 1115)
                .boxed()
                .toList();

        String invertedOddNumbers = numbers
                .stream()
                .filter(it -> it % 2 != 0) // logpoint above this line, for example
                .map(it -> -it)
                .map(Objects::toString)
                .collect(Collectors.joining("; "));




        System.out.println("one");
        System.out.println("two");
        System.out.println("three");

//        IntStream.range(0, 3).forEach(i -> System.out.println(
//                " From editor: Internal: " + i));

    }/**/

    private static boolean isInterested(int i) {
        return i % 2 == 0;
    }

}

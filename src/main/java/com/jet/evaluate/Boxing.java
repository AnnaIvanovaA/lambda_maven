package com.jet.evaluate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Boxing {
    public static void main(String[] args) {

        File file = new File("foo");
        System.out.println(file);

        Character ch = 'a';


        Map<Integer, Integer> map = Stream.iterate(0, i -> i + 1).limit(200).collect(Collectors.toMap(k -> k, k -> k * k));
        System.out.println("hello"); // Breakpoint

        List<Integer> li = new ArrayList<>();
        for (int i = 1; i < 50; i += 2)
            li.add(i);
    }
}

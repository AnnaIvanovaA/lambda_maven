package com.jet.lambda_tutorials;

import java.util.*;
import java.util.stream.Collectors;

public class ConvertUsingStream {

    public static void main(String[] args) {

        List<String> x = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            x.add(String.valueOf(i));
        }

int k =5;
k++;

        List<Integer> y = x.stream()
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());

        Map<String, String> xMap = new HashMap<>();
        xMap.put("1", "0");
        xMap.put("2", "1");
        xMap.put("3", "2");
        xMap.put("4", "3");
        Map<String, Integer> yMap =
                xMap.entrySet().stream()
                        .collect(Collectors.toMap(
                                e -> e.getKey(),
                                e -> Integer.parseInt(e.getValue())
                        ));
        System.out.println();

    }
}

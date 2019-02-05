package com.jet.lambda_tutorials;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConvertUsingStream {

    public static void main(String[] args) {
        List<String> x = new ArrayList<>();
        x.add("12");
        x.add("34");
        List<Integer> y = x.stream()
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());

        Map<String, String> xMap = new HashMap<>();
        xMap.put("1", "0");
        xMap.put("2", "1");
        xMap.put("3", "2.0d");
        xMap.put("4", "3L");
        Map<String, Integer> yMap =
                xMap.entrySet().stream()
                        .collect(Collectors.toMap(
                                e -> e.getKey(),
                                e -> Integer.parseInt(e.getValue())
                        ));

    }
}

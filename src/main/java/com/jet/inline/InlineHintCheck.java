package com.jet.inline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InlineHintCheck {
    public static void main(String[] args) {
        // Primitive array
        int[] numbers = {1, 2, 3, 4, 5};

        // List of strings
        List<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");

        // Map with name-age pairs
        Map<String, Integer> ageMap = new HashMap<>();
        ageMap.put("Alice", 30);
        ageMap.put("Bob", 25);
        ageMap.put("Charlie", 28);

        // Debugging through arrays, lists, and maps
        for (int i = 0; i < numbers.length; i++) {
            System.out.println("Number: " + numbers[i]);

            // Inline hint for List
            System.out.println("Name from list: " + names.get(i));

            // Inline hint for Map
            String name = names.get(i);
            Integer age = ageMap.get(name);
            System.out.println(name + " is " + age + " years old.");
        }
    }
}

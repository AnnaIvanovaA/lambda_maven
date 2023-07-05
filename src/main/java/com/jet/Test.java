package com.jet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String n1 = reader.readLine();
            String n2 = reader.readLine();
            String n3 = reader.readLine();
            String n4 = reader.readLine();
            String n5 = reader.readLine();
            String n6 = reader.readLine();
            System.out.println(n1 + n2 + n3 + n4 + n5 + n6);
            System.out.println(n1 + n2 + n3 + n4 + n5 + n6);
            System.out.println(n1 + n2 + n3 + n4 + n5 + n6);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("sdfsd");
        System.out.println(getMessage());           // (1)
        System.out.println("You shall not pass!");  // (2)


        List<Person> pl = Person.createShortList();

        //can be replaced
        pl.forEach( p -> p.printWesternName() );


        //updated but never queried
        List<Integer> numbersList1 = new ArrayList<>();
        for (int i = 0; i < 150; i++) {
            numbersList1.add(i);
        }

        Map<String,Integer> someMap = new HashMap<>();
        someMap.put("smt", 3);

    }

    private static String getMessage() {
        return "Hello, bug!";
    }

}

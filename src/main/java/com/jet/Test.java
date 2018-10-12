package com.jet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

    public static void main(String[] args) {
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


}

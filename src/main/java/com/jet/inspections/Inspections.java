package com.jet.inspections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inspections {


    public static int[] getArray(){
        int[] array = {1, 2, 3, 4, 5, 6};
        return array;
    }

    public static List<Integer> getIntegerList(){
        List<Integer> intList = new ArrayList<>();
        for (int i = 0; i < 100 ; i++) {
            intList.add(i);
        }
        return intList;
    }

    public static Map<Integer, String> getMap(){
        Map<Integer, String> stringMap = new HashMap<>();
        for (int i = 0; i < 20; i++) {
            stringMap.put(i, i + " ");
        }
        return stringMap;
    }
}

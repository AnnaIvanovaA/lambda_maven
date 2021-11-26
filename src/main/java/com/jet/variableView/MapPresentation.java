package com.jet.variableView;

import java.time.LocalDateTime;
import java.util.*;

//TODO: something here

public class MapPresentation {
    public static void main(String[] args) {

        boolean result = true;
        char capitalC = 'C';
        char c = '\uffff';
        char c2 = '\u0108';
        char c3 = '\u0000';
        char c4 = '\u039A';

        byte b = 100;
        short s = 10000;
        int i = 100000;
        float f = 3.0f;
        double d2 = 1.234e2;

        // The number 26, in decimal
        int decVal = 26;
//  The number 26, in hexadecimal
        int hexVal = 0x1a;
// The number 26, in binary
        int binVal = 0b11010;


        //Underscore in numerics
        long creditCardNumber = 1234_5678_9012_3456L;
        long socialSecurityNumber = 999_99_9999L;
        float pi =  3.14_15F;
        long hexBytes = 0xFF_EC_DE_5E;
        long hexWords = 0xCAFE_BABE;
        long maxLong = 0x7fff_ffff_ffff_ffffL;
        byte nybbles = 0b0010_0101;
        long bytes = 0b11010010_01101001_10010100_10010010;

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1, 2);
        map.put(2, 2);
        System.out.println(map);


        Random random = new Random(36);
        Map<Integer, Integer> hashMap = new HashMap<>();

        for (int k = 0; k < 11000; k++){
            int number = random.nextInt(1100);
            Integer frequency = hashMap.get(number);
            hashMap.put(number, frequency == null ? 1 : frequency + 1);
        }
        System.out.println(hashMap);


        Map<String, Object> mapPrimitiveTypes = new HashMap<>();

        mapPrimitiveTypes.put("a", "str");
        mapPrimitiveTypes.put("b", 1);
        mapPrimitiveTypes.put("c", 2.0d);
        mapPrimitiveTypes.put("d", 3L);
        mapPrimitiveTypes.put("e", System.currentTimeMillis());
        mapPrimitiveTypes.put("f", new Date());
        mapPrimitiveTypes.put("g", LocalDateTime.now());
        mapPrimitiveTypes.put("h", 'c');

        mapPrimitiveTypes.put("a", null);
        mapPrimitiveTypes.put("g", null);

        mapPrimitiveTypes.put("j", result);
        mapPrimitiveTypes.put("k", capitalC);
        mapPrimitiveTypes.put("l", c);
        mapPrimitiveTypes.put("m", c2);
        mapPrimitiveTypes.put("m2", c3);
        mapPrimitiveTypes.put("m3", c4);

        mapPrimitiveTypes.put("n", b);
        mapPrimitiveTypes.put("o", s);
        mapPrimitiveTypes.put("p", i);
        mapPrimitiveTypes.put("q", f);
        mapPrimitiveTypes.put("r", d2);
        mapPrimitiveTypes.put("s", decVal);
        mapPrimitiveTypes.put("t", hexVal);
        mapPrimitiveTypes.put("u", binVal);
        mapPrimitiveTypes.put("v", creditCardNumber);
        mapPrimitiveTypes.put("w", socialSecurityNumber);
        mapPrimitiveTypes.put("x", pi);
        mapPrimitiveTypes.put("y", hexWords);
        mapPrimitiveTypes.put("z", hexBytes);
        mapPrimitiveTypes.put("r1", maxLong);
        mapPrimitiveTypes.put("r2", nybbles);
        mapPrimitiveTypes.put("r3", bytes);

        Object obj = mapPrimitiveTypes.get("q");

        System.out.println(mapPrimitiveTypes.size());

        HashMap<String, String> capitalCities = new HashMap<>();

        capitalCities.put("England", "London");
        capitalCities.put("Germany", "Berlin");
        capitalCities.put("Norway", "Oslo");
        capitalCities.put("USA", "Washington DC");
        System.out.println(capitalCities);

        HashMap<ParentClass, List<? extends ParentClass>> complexMap = new HashMap<>();

        complexMap.put(new ParentClass("1"), Arrays.asList(new Child1("ch1"), new Child2("ch2")));
        complexMap.put(new ParentClass("2"), Arrays.asList(new Child1("ch11"), new Child2("ch22"), new Child1("ch111"), new Child2("ch222")));

        System.out.println("complexMap: " + complexMap);
        System.out.println("complexMap.keySet(): " + complexMap.keySet());

        for(ParentClass parent : complexMap.keySet()){
            System.out.println(parent + " has");
            for (ParentClass child : complexMap.get(parent)){
                System.out.println("  " + child);
            }
        }
        System.out.println();

        TreeMap<Integer, Set<String>> testMap = new TreeMap<>();
        Set<String> set1 = new TreeSet<>();
        set1.add("smth1");
        set1.add("smth2");
        set1.add("smth3");
        Set<String> set2 = new TreeSet<>();
        set2.add("smth11");
        set2.add("smth22");
        set2.add("smth33");
        set2.add("smth44");
        testMap.put(1, set1);
        testMap.put(2, set2);

        System.out.println();
    }
}

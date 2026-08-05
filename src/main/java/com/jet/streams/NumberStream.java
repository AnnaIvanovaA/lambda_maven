package com.jet.streams;

import java.util.List;
import java.util.stream.Collectors;


public class NumberStream {
    private static final double PRICE=Math.max(1.0, 2.0);
     static final double[] dPoint = new double[10];

    public static void main(String[] args) throws InterruptedException {

        Thread.sleep(3000);
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        //final double[] dPoint = new double[numbers.size()];
        System.out.println("fsd");

        String s = "1234567890";
        Integer.parseInt(s);
        System.out.println(s.length());
        System.out.println(s.charAt(0));
        System.out.println(s.substring(0, 3));


        System.out.println(s.substring(0, 3).toUpperCase());
        System.out.println(s.substring(0, 3).toLowerCase());
        System.out.println(s.substring(0, 3).replace("3", "9"));

        String s1 = "1234567890";
        String s2 = s1.substring(0, 3).replace("3", "9");
        System.out.println(s2);
        System.out.println(s1);

        Enum.valueOf(Enum.class, "A").name();
        Enum.valueOf(Enum.class, "A").toString();

        Enum.valueOf(Enum.class, "A");



        String invertedOddNumbers = numbers
                .stream()
                .filter(it -> it % 2 != 0).map(it -> -it)
                .map(Object::toString)
                .collect(Collectors.joining("; "));
        System.out.println(invertedOddNumbers);
    }
            }
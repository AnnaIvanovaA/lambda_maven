package com.jet.inspections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        String a = "xxx";
        a = new String(a);


        int[] ia1 = new int[1000];
        ia1[1] = 1;
        ia1[10] = 10;
        ia1[100] = 101;
        try{
            System.out.println("Hello");
        }
        catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(ia1[101]);


        String str;
        float fl = (float) 'a';


        int[] anArray = Inspections.getArray();


        List<Integer> intList = Inspections.getIntegerList();
        List<Integer> nullList = new ArrayList<>();

        Map<Integer, String> stringMap = Inspections.getMap();
        System.out.println();
    }



}

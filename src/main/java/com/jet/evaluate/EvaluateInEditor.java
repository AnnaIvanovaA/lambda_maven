package com.jet.evaluate;

import com.jet.rendererTypeCheck.LongString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EvaluateInEditor {

    public static void main(String[] args) {


        String s = "Hello, World!\nHello, World!";



        BigDecimal one = BigDecimal.ONE;

        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        String str = list.get(0);
        System.out.println(list.get(2));


        String strCutted = LongString.stringLength1412;
        String strFullLongest = LongString.stringLength4236;

        System.out.println(LongString.stringLength706);
    }
}

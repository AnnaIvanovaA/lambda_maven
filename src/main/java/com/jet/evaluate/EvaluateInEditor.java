package com.jet.evaluate;

import com.jet.rendererTypeCheck.LongString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EvaluateInEditor {

    public static void main(String[] args) {

        try {
            Integer integer = null;
//            integer.getClass();

            throw new ThrowException();
        } catch (Exception e) {
            StackTraceElement element = e.getStackTrace()[0];
            StackTraceElement trace = new StackTraceElement("com.jet.evaluate.EvaluateInEditor", "main" , "EvaluateInEditor.java", 5 );
            StackTraceElement[] methods = Thread.currentThread().getStackTrace();
            System.out.println("dsfdsf");
        }


        String strr = "Hello, World!\nHello, World!";


        //Evaluate
        for (String s : Arrays.asList("sad", "sd")) {
            if (s.equals("sdf")) {
                return;
            }
        }
        Runnable xxxxx = () -> System.out.println(1);
        //

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

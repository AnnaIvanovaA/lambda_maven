package com.jet.evaluate;

import com.jet.rendererTypeCheck.LongString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class EvaluateInEditor {

    public static void main(String[] args) {

        try {
System.out.println("dsfsdfsdf");
//System.out.println();
            Integer integer = null;
//            integer.getClass();

            throw new ThrowException();
        } catch (Exception e) {
            StackTraceElement element = e.getStackTrace()[0];
            StackTraceElement trace = new StackTraceElement("com.jet.evaluate.EvaluateInEditor", "main" , "EvaluateInEditor.java", 5 );
            StackTraceElement[] methods = Thread.currentThread().getStackTrace();
            System.out.println("dsfdsf");
        }

        List<String> list = new ArrayList<>();
        list.add("abc");
        list.add("abcaba");
        list.add("a");
        list.add("bca");

        String sghtr = "----> file:///home/Anna.Ivanova/IdeaProjects/lambda_maven/src/main/java/com/jet/terminal/UtfChefile:///home/Anna.Ivanova/IdeaProjects/lambda_maven/src/main/java/com/jet/terminal/UtfCheck.java:27:9----> file:///home/Anna.Ivanova/IdeaProjects/lambda_maven/src/main/java/com/jet/terminal/UtfCheck.java:27:9";

        Stream<String> myStream = list.stream();
        myStream.filter(it -> it.startsWith("a")).anyMatch(it -> it.length() > 1);

        String strr = "Hello, World!\nHello, World!";


        //Evaluate
        for (String s : Arrays.asList("sad", "sd")) {
            if (s.equals("sdf")) {
                return;
            }
        }
        Runnable xxxxx = () -> System.out.println(1);
        //comments

        BigDecimal one = BigDecimal.ONE;

        List<String> list2 = new ArrayList<>();
        list2.add("one");
        list2.add("two");
        list2.add("three");
        String str = list2.get(0);
        System.out.println(list2.get(2));


        String strCutted = LongString.stringLength1412;
        String strFullLongest = LongString.stringLength4236;

        System.out.println(LongString.stringLength706);
        System.out.println();
        System.out.println();

    }
}

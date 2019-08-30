package com.jet.evaluate;

import java.util.List;

public class CastInEvaluator {

    public static void main(String[] args) {

        int a = 5;

        System.out.println((Object)a);

        /*
        not fixed yet IDEA-203279
        Expected result: ClassCastException
        Cannot cast 'com.jet.evaluate.CastInEvaluator$1Local' to 'com.jet.evaluate.CastInEvaluator'
        evaluate (Local)l, see the error Cannot cast 'my.eval.casts.CastLocal$1Local' to 'Local'
        */
        class Local {}
        Object l = new Local();
        Local l1 = (Local)l; // BP here


        /* Expected result: ClassCastException
        Cannot cast 'java.lang.Integer' to 'java.util.List'
        run and evaluate:
        List list = (List) o;
        list.size();
         */
        Object o = new Integer(0);
        o=o; // breakpoint here

        //fail class cast exc in the runtime
        List list = (List) o;
        list.size();

        System.out.println();


    }


    /*case 3
    Evaluate --
Expected result: cannot cast to Integer (class cast exception)
Evaluate this ->
Integer integer = getT();
integer = integer+10;
`   */

    public static <T> T getT() {
        return (T) "HelloWorld";
    }
}
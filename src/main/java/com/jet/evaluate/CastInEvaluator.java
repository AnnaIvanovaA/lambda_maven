package com.jet.evaluate;

import java.util.List;

public class CastInEvaluator {

    public static void main(String[] args) {

        //Expected result: ClassCastException
        // Cannot cast 'com.jet.evaluate.CastInEvaluator$1Local' to 'com.jet.evaluate.CastInEvaluator'
        //evaluate (Local)l, see the error Cannot cast 'my.eval.casts.CastLocal$1Local' to 'Local'
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

}
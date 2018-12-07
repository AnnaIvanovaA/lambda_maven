package com.jet.reproduce;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        foo();

        LocalVar localVar = new LocalVar();
        localVar.setAnotherLabel("x1");

        int n;
        String s;

        someMethod();
        System.out.println("stmg");

    }

    public static void someMethod(){
        final HashMap<GiantClassNameToReproduceAnIssueFromYouTrack, HashMap<VeryLongClassNameToReproduceBug, HashMap<LongClassNameToReproduceBug, String>>>
                nameHashMap = new HashMap<>();
    }

    private static boolean foo() {
        int a =5; // here
        return false;
    }



}

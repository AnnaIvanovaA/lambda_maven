package com.jet.breakpoints;

public class InstrumentationLocalVar {

    private static void localVariableCase() {
        int local = 10;
        System.out.println("local marker"); // Logpoint: local = 42
        System.out.println("local=" + local); // Expected: local=42 -- doesn't work in 262
    }

    private static void parameterCase(String parameter) {
        System.out.println("parameter marker"); // Logpoint: parameter = "mutated-parameter"
        System.out.println("parameter=" + parameter); // Expected: parameter=mutated-parameter -- doesn't work
    }

    public static void main(String[] args) {
        localVariableCase();
        parameterCase("original-parameter");
    }
}

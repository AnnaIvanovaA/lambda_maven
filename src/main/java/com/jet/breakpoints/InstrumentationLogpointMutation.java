package com.jet.breakpoints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Reproducer for IJPL-251143
 */
public class InstrumentationLogpointMutation {
    private String instanceField = "original-instance";
    private static String staticField = "original-static";

    private static void localVariableCase() {
        int local = 10;
        System.out.println("local marker"); // Logpoint: local = 42
        System.out.println("local=" + local); // Expected: local=42 -- doesn't work in 262
    }

    private static void parameterCase(String parameter) {
        System.out.println("parameter marker"); // Logpoint: parameter = "mutated-parameter"
        System.out.println("parameter=" + parameter); // Expected: parameter=mutated-parameter -- doesn't work
    }

    private void fieldCases() {
        System.out.println("instance field marker"); // Logpoint: instanceField = "mutated-instance"
        System.out.println("instanceField=" + instanceField); // Expected: mutated-instance

        System.out.println("static field marker"); // Logpoint: staticField = "mutated-static"
        System.out.println("staticField=" + staticField); // Expected: mutated-static
    }

    private static void referenceAndArrayCases() {
        String reference = "original-reference";
        int[] numbers = {1, 2, 3};

        System.out.println("reference marker"); // logpoint: reference = "mutated-reference"
        System.out.println("reference=" + reference); // Expected: mutated-reference

        System.out.println("array marker"); // Logpoint: numbers[0] = 99
        System.out.println("numbers=" + Arrays.toString(numbers)); // Expected: [99, 2, 3]
    }

    private static void methodSideEffectCase() {
        List<String> values = new ArrayList<>();
        System.out.println("method side-effect marker"); // Logpoint: values.add("added-by-breakpoint")
        System.out.println("values=" + values); // Expected: [added-by-breakpoint]
    }

    private static void repeatedHitsCase() {
        int mutatedOnEveryHit = 0;
        for (int iteration = 1; iteration <= 3; iteration++) {
            System.out.println("loop marker " + iteration); // Logpoint: mutatedOnEveryHit += 10
        }
        System.out.println("mutatedOnEveryHit=" + mutatedOnEveryHit); // Expected: 30
    }

    private static void conditionalCase() {
        int mutationCount = 0;
        for (int iteration = 1; iteration <= 3; iteration++) {
            // BREAKPOINT; Condition: iteration == 2; Evaluate and log: mutationCount++
            System.out.println("conditional marker " + iteration);
        }
        System.out.println("mutationCount=" + mutationCount); // Expected: 1 -- doesn't work
    }

    public static void main(String[] args) {
        localVariableCase();
        parameterCase("original-parameter");
        new InstrumentationLogpointMutation().fieldCases();
        referenceAndArrayCases();
        methodSideEffectCase();
        repeatedHitsCase();
        conditionalCase();
    }
}

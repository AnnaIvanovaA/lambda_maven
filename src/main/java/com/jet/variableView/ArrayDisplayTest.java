package com.jet.variableView;

public class ArrayDisplayTest {
    public static void main(String[] args) {
        // Integer array with 1000 elements
        int[] intArray = new int[1000];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = i;
        }

        // Double array with 2000 elements
        double[] doubleArray = new double[2000];
        for (int i = 0; i < doubleArray.length; i++) {
            doubleArray[i] = i * 0.5;
        }

        // String array with 3000 elements
        String[] stringArray = new String[3000];
        for (int i = 0; i < stringArray.length; i++) {
            stringArray[i] = "String #" + i;
        }

        // Boolean array with 4000 elements
        boolean[] booleanArray = new boolean[4000];
        for (int i = 0; i < booleanArray.length; i++) {
            booleanArray[i] = (i % 2 == 0); // true for even indices, false for odd
        }

        // Character array with 5000 elements
        char[] charArray = new char[5000];
        for (int i = 0; i < charArray.length; i++) {
            charArray[i] = (char) (65 + (i % 26)); // Repeating A-Z characters
        }

        // Long array with 6000 elements
        long[] longArray = new long[6000];
        for (int i = 0; i < longArray.length; i++) {
            longArray[i] = i * 1000L;
        }

        // Float array with 7000 elements
        float[] floatArray = new float[7000];
        for (int i = 0; i < floatArray.length; i++) {
            floatArray[i] = i * 0.1f;
        }

        Person[] peopleArray = new Person[5000];

        for (int i = 0; i < peopleArray.length; i++) {
            peopleArray[i] = new Person("Person #" + i, 20 + (i % 30), "person" + i + "@example.com");
        }

        String[] bigStringArray = new String[2000];

        for (int i = 0; i < bigStringArray.length; i++) {
            bigStringArray[i] = "This is a big string. Repeated text for testing. Element #" + i + ". "
                    + "link https://plugins.jetbrains.com/. "
                    + "This string is repeated multiple times to increase its length. ".repeat(100);
        }


        System.out.println("All arrays have been initialized");
    }
}


package com.jet.breakpoints;

/*
Class to check all types of icons
 */

import java.util.ArrayList;

public class NewIconsAllTypes {

    //field
    private static Integer intVar; //set BP - 2 options: field and line BPs -- from 182 click - watchpoint, alt+click - line BP
    private static String strVar; //valid field BP
    private static ArrayList<String> listStr; //disabled field BP
    int var1; // valid + do not suspend BP + condition
    int var2; // disabled + do not suspend BP --yellow

    //line

    private int array[]; //enabled line BP -- invalid during debugging session
    private int length;  //disabled line BP
    int var3; // valid + do not suspend BP + + condition -- invalid during debugging session
    int var4; // disabled + do not suspend BP --yellow


    public static void someMethod() {  //valid method BP
        intVar = 50; //BP here
        System.out.println("someMethod is working"); //BP here
    } //folded method with 2BPs inside

    public static void someMethod2() {  //valid method BP
        intVar = 50; //BP here
        System.out.println("someMethod is working"); //BP here
    } //disabled

    public static void someMethod3() {  //valid method BP
        intVar = 50; //BP here
        System.out.println("someMethod is working"); //BP here
    } // valid + do not suspend BP + condition

    public static void someMethod4() {  //valid method BP
        intVar = 50; //BP here
        System.out.println("someMethod is working"); //BP here
    } // disabled + do not suspend BP --yellow


    //dependent watchpoint
    int varInt;
    //dependent line BP
    ArrayList<String> list = new ArrayList<>();

    //dependent method BP
    public static void dependentMethod() {

    }


    public void sort(int[] inputArr) {

        if (inputArr == null || inputArr.length == 0) {
            return;
        }
        this.array = inputArr;
        length = inputArr.length;
        quickSort(0, length - 1);
    }

    private void quickSort(int lowerIndex, int higherIndex) {

        int i = lowerIndex;
        int j = higherIndex;
        int pivot = array[lowerIndex + (higherIndex - lowerIndex) / 2];
        while (i <= j) {
            while (array[i] < pivot) {
                i++;
            }
            while (array[j] > pivot) {
                j--;
            }
            if (i <= j) {
                exchangeNumbers(i, j);
                i++;
                j--;
            }
        }
        if (lowerIndex < j)
            quickSort(lowerIndex, j);
        if (i < higherIndex)
            quickSort(i, higherIndex);
    }

    private void exchangeNumbers(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String a[]) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        NewIconsAllTypes sorter = new NewIconsAllTypes();
        int[] input = {24, 2, 45, 20, 56, 75, 2, 56, 99, 53, 12};
        sorter.sort(input);
        for (int i : input) {
            System.out.print(i);
            System.out.print(" ");
        }
    }

}

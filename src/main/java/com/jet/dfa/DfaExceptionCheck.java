package com.jet.dfa;

import java.time.LocalDate;
import java.util.Date;

public class DfaExceptionCheck {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int sum = 0;
        for (int i = 0; i <= arr.length; i++) {
            System.out.println("Processing arr[" + i + "]");
            sum += arr[i];
        }

    }
}

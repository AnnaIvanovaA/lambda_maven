package com.jet.reproduce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Issue {
    public static void main(String[] args) throws IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while (!((line = rd.readLine()).equals("exit"))){
            if (line.equals("test")){
                try {
                    System.out.println("Test test");   //BP here
                } catch (Exception ex) {
                    ex.printStackTrace();               // Run to cursor here
                }
            }
        }

    }

}

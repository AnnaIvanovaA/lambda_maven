package com.jet.breakpoints;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInput3 {

    public static void main(String[] args) {
        ConsoleInput3 consoleInput = new ConsoleInput3();

        try{
            System.out.println("I'm waiting...");
            consoleInput.waitForConsoleInput();
        }
        catch (Exception e){
            System.out.println("caught!");
        }

    }

    public void waitForConsoleInput() throws IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while (!((line = rd.readLine()).equals("exit"))){
            final String rdStr = rd.getClass().getName();
            System.out.println(line);

            for (int i = 0; i < 5; i++) {
                final String checkString = "smth"+i;
                if (checkString.equals("smth0")){
                    i++;
                }
                else if (checkString.equals("smth1")){
                    i++;
                }
                else{
                    i=i*2;
                }
            }

        }
    }
}

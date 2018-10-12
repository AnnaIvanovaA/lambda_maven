package com.jet.breakpoints;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInput {

    public static void main(String[] args) {
        ConsoleInput consoleInput = new ConsoleInput();
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
            System.out.println(line);
        }
    }
}

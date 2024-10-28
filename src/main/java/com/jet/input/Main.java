package com.jet.input;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while(!input.isEmpty()){
            scanner.nextLine();
        }

        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String name = bufferedReader.readLine(); //читаем строку с клавиатуры
        String sAge = bufferedReader.readLine(); //читаем строку с клавиатуры
        int nAge = Integer.parseInt(sAge); //преобразовываем строку в число.

    }
}

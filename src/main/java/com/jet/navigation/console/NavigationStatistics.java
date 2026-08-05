package com.jet.navigation.console;

import java.io.IOException;

public class NavigationStatistics {
    public static void main(String[] args) throws IOException {
        new ProcessBuilder("bash", "-c", "echo 'Hi from bash!'")
                .inheritIO()
                .start();
        System.out.println("Hello!");
        System.out.println("World!");
    }
}

package com.jet.terminal;

public class TerminalOutput {
    public static void main(String[] args) throws InterruptedException {
        String line = "sdfsdfsd sdfdsfdsfdsfsdf sdfdsfsdfdsf sde ewg regergregregr efwefew";
        String[] words = line.split(" ");
        for (int i = 0; i < 10000; i++) {
            Thread.sleep(1);
            for (String word : words) {
                System.out.print(word + " ");
            }
            System.out.println();
        }
    }

}

package com.jet.hotswap;

public class HotSwapCheck {

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            printMessage();
            Thread.sleep(3000);
        }
    }

    public static void printMessage() {
        System.out.println("some message"); // <- modify this during hotswap
    }

}

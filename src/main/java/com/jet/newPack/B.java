package com.jet.newPack;

import java.io.IOException;

public class B {
    public static void main(String[] args) throws InterruptedException {
        try {
            System.out.println("dsdfdsfd");
            System.in.read();
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("new");
        Thread.sleep(100000000);


    }

}

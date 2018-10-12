package com.jet.evaluate;

import java.io.FileNotFoundException;

public class ThrowException {

    public static void main(String[] args) {

        try{
            System.out.println("Hello");
            //throw new FileNotFoundException();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}

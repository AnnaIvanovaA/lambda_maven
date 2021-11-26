package com.jet.evaluate;

import java.io.FileNotFoundException;

public class ThrowException extends Exception {

    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("hello");
        throw new FileNotFoundException();

//        try{
//            System.out.println("Hello");
//            throw new FileNotFoundException();
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }

    }
}

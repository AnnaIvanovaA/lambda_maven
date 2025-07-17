package com.jet.framesView;

public class ThrowExceptionCheck {

    public static void main(String[] args) {
        try {
            greet();
        }
        catch (NullPointerException e) {
            System.out.println("Exception was caught");
        }
    }

    public static void greet(){
        for (int i = 0; i < 20; i++) {
            //stop with BP here and invoke ThrowException from view, pass new NPE() there
            System.out.println("Greet # " + i);
        }
    }
}

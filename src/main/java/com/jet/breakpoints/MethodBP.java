package com.jet.breakpoints;

public class MethodBP {
    public static void main(String[] args) {
        funcMethod();
    }

    private static void funcMethod() {
        int val =3;

        if (val == 1){
            return;
        }

        if (val == 1) {return;}

        if (val == 3) {
            return;
        }

        return;

    }
}

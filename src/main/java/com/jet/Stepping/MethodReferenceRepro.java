package com.jet.Stepping;

import java.util.ArrayList;
import java.util.List;

public class MethodReferenceRepro {
    public static void main(String[] args) {
        List<Runnable> actions = new ArrayList<>();
        actions.add(new Runnable() {

            @Override
            public void run() {
                System.out.println("test");
            }
        });

        actions.forEach(Runnable::run);
    }
}

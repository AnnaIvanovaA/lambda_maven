package com.jet.breakpoints;

public class S {
    public static void main(String[] args) {
        Neighbor neighbor = new Neighbor();
        neighbor.f = 20;
        // Breakpoint!
        int a = 42;

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(neighbor.f);
            }
        };
        r.run();

    }
}

class Neighbor {
    int f = 10;
}

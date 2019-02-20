package com.jet;

public class Main {

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            if (foo(i) == 12 || foo(i) == 10 || foo(i) == 15) {
                continue;
            }
            System.out.println(i);
        }

        System.out.println("hello");


        try {
            Test01foreach.main(args);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Test02Filter.main(args);
        Test03toList.main(args);
        Test04Map.main(args);

        System.arraycopy(args, System.identityHashCode(args), args, System.identityHashCode(args), System.identityHashCode(args));
        System.out.println("chaaaange");
    }

    private static int foo(int i) {
        return i;
    }
}

package com.jet;

public class Main {

    public static void main(String[] args) {

        try {
            Test01foreach.main(args);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Test02Filter.main(args);
        Test03toList.main(args);
        Test04Map.main(args);

        System.arraycopy(args, System.identityHashCode(args), args, System.identityHashCode(args), System.identityHashCode(args));
        System.out.println("qwer");
        System.out.println("new commit");
    }
}

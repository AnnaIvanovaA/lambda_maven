package com.jet.dfa;

public class Try {

    public static final void main(String[] args) throws Exception {
        try {
            findElvis();
        } catch (Throwable th) {
            if (th instanceof Error) { // 'th instanceof Error' always 'false'
                System.err.println("IntelliMajigger fail");
            }
        }
        try {
            findElvis();
        } catch (Throwable th) {
            if (!(th instanceof RuntimeException)) { // 'th instanceof RuntimeException' always 'true'
                System.err.println("IntelliMajigger fail mk2");
            }
        }
    }

    private static void findElvis() {
        throw new AssertionError("The king is dead.");
    }
}

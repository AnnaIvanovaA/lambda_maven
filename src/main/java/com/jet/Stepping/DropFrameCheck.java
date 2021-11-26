package com.jet.Stepping;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class DropFrameCheck {
    public static void main(String[] argv) {
        DropFrameCheck longFinally = new DropFrameCheck();
        longFinally.doLong();
    }

    public void doLong() {
        try {
            FileInputStream fis = new FileInputStream("not existent");
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        } finally {
            System.out.println("finally: line 1");
            System.out.println("finally: line 2"); // Set breakpoint here.
        }
    }
}

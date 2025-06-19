package com.jet.asyncstack;

import javax.swing.*;

public class AsyncStacktrace {
    public static void main(String[] args) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        SwingUtilities.invokeLater(() -> foo());
    }
    private static void foo() {
        System.out.println("Later"); // breakpoint here
    }
}

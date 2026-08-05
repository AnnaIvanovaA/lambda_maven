package com.jet.navigation.console;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class JUnitHighlight {
    @Test
    void one() {
        System.out.println("ping 2");
        System.out.println("ping common");
        fail("Message from Assert");
    }

    @Test
    void two() throws InterruptedException {
        System.out.println("ping 1");
        System.out.println("ping common");
        Thread.sleep(5000);
    }

    @Test
    void threeManyLines() {
        for (int i = 0; i < 100_000; i++) {
            System.out.println("test tick " + i);
        }
    }
}

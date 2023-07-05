package com.jet.coverage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    void foo() {
        Main main = new Main();
        Assertions.assertTrue(main.foo());
    }
}

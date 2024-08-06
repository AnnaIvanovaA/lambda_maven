package testpack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestClass {
    @Test
    public void testAddition() throws InterruptedException {
        runTest(2 + 2, 4);
    }

    @Test
    public void testAdditionWithDifferentExpected() {
        runTest(2 + 2, 4);
    }

    @Test
    public void testAdditionIncorrectResult() {
        runTest(2 + 2, 5);
    }

    @Test
    public void testAdditionAnotherIncorrectResult() {
        runTest(2 + 2, 6);
    }

    private void runTest(int actual, int expected) {
        System.out.println();
        assertEquals(expected, actual);
    }
}

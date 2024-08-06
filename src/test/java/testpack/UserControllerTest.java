package testpack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserControllerTest {

    @Test
    public void testAdditionEqualsFour() {
        performAdditionTest(4);
    }

    @Test
    public void testAdditionEqualsFourDuplicate() {
        performAdditionTest(4);
    }

    @Test
    public void testAdditionEqualsFive() {
        performAdditionTest(5);
    }

    @Test
    public void testAdditionEqualsSix() {
        performAdditionTest(6);
    }

    private void performAdditionTest(int expected) {
        System.out.println();
        assertEquals(2 + 2, expected);
    }
}

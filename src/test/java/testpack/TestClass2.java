package testpack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TestClass2 {

    @Test
    public void test_Method() throws InterruptedException {
        //Thread.sleep(2000);
        System.out.println();
        assertEquals(2+2, 20);
    }
}

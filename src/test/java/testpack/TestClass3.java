package testpack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestClass3 {

    @Test
    public void test_Method(){
        System.out.println("output from the test");
        assertEquals(2+2, 4);
    }
}

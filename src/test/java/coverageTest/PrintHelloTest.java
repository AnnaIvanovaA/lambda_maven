package coverageTest;

import com.jet.coverage.PrintHello;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrintHelloTest {
    private final PrintHello service = new PrintHello();

    @Test
    void greetWithName() {
        assertEquals("Hello, Anna!", service.greet("Anna"));
    }

    @Test
    void greetWithoutName() {
        assertEquals("Hello, stranger!", service.greet(""));
    }

    @Test
    public void test_Method(){
        System.out.println("output from the test");
        assertEquals(2+2, 20);
    }
}

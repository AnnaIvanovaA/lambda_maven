package testpack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestClass4 {

    @Test
    public void test_Method(){
        System.out.println();
        assertEquals(2+2, 4);


    }

    @Test
    public void test_Method2(){
        System.out.println();
        assertEquals(2+2, 4);
    }
    @Test
    public void test_Method3(){
        System.out.println();
        assertEquals(2+2, 5);
    }
    @Test
    public void test_Method4(){
        System.out.println();
        assertEquals(2+2, 6);
    }
}

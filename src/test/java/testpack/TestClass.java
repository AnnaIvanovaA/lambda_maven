package testpack;

import org.junit.Assert;
import org.junit.Test;


public class TestClass {

    @Test
    public void test_Method() throws InterruptedException {
        System.out.println();
        Assert.assertEquals(2+2, 4);
    }

    @Test
    public void test_Method2(){
        System.out.println();
        Assert.assertEquals(2+2, 4);
    }
    @Test
    public void test_Method3(){
        System.out.println();
        Assert.assertEquals(2+2, 5);
    }
    @Test
    public void test_Method4(){
        System.out.println();
        Assert.assertEquals(2+2, 6);
    }
}

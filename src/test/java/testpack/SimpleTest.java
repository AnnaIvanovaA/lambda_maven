package testpack;

import org.junit.jupiter.api.Test;

public class SimpleTest {
    @Test
    public void smth() throws InterruptedException {
        int i = 0;
        while (i!=1000000) {
            i++;
            System.out.println("line");
            System.out.print("I'm still alive: ");
            System.out.println(i);
            System.err.println("BLAH");
            Thread.sleep(5000);
        }
    }
}

package testpack;

import org.junit.Test;

public class SimpleTest {
    @Test
    public void smth(){
        int i = 0;
        while (i!=1000000) {
            i++;
            System.out.print("I'm still alive: ");
            System.out.println(i);
            System.err.println("BLAH");
        }
    }
}

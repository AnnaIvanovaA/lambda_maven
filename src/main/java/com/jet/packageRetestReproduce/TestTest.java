package com.jet.packageRetestReproduce;
import com.jet.intentions.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestTest {

    @Test
    public void testSimpleTest() {

        User user = new User(100, "Tom");
        user.boo();

        assertEquals(1, 1);
    }
}

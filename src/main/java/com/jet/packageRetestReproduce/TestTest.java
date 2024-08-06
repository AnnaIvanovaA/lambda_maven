package com.jet.packageRetestReproduce;
import com.jet.intentions.User;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTest {

    @Test
    public void testSimpleTest() {

        User user = new User(100, "Tom");
        user.boo();

        assertEquals(1, 1);
    }
}

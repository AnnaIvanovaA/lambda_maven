package coverageTest;

import com.jet.coverage.AddAndDivide;
import com.jet.coverage.UserService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserServiceTest {
    private final UserService userService = new UserService(new AddAndDivide());

    @Test
    void calculateUserScoreValid() {
        assertEquals(8, userService.calculateUserScore(10, 2));
    }

    @Test
    void calculateUserScoreInvalid() {
        assertThrows(IllegalArgumentException.class, () -> userService.calculateUserScore(-1, 0));
    }
}

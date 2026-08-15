package reverseTask;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUntillTest {

    @Test
    void testReverseLetters() {
        assertEquals("t@eb eht av$J!123", StringUntill.reverseLetters("J@va the be$t!123"));
        assertEquals("", StringUntill.reverseLetters(""));
        assertEquals("a", StringUntill.reverseLetters("a"));
        assertEquals("123 @#!", StringUntill.reverseLetters("123 @#!"));
        assertEquals("dcba", StringUntill.reverseLetters("abcd"));
        assertEquals("!c@b#a$", StringUntill.reverseLetters("!a@b#c$"));
        assertEquals("A@b", StringUntill.reverseLetters("b@A"));
        assertNull(StringUntill.reverseLetters(null));
    }
}

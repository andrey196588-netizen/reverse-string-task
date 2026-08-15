package reverseTask;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUntillTest {

    @Test
    void shouldReverseStandardString() {
        assertEquals("t@eb eht av$J!123", StringUntill.reverseLetters("J@va the be$t!123"));
    }
    @Test
    void returnsEmptyForEmptyInput() {
        assertEquals("", StringUntill.reverseLetters(""));
    }
    @Test
    void shouldHandleSingleLetter() {
        assertEquals("a", StringUntill.reverseLetters("a"));
    }

    @Test
    void keepsNonLettersInPlace() {
        assertEquals("123 @#!", StringUntill.reverseLetters("123 @#!"));
        assertEquals("!c@b#a$", StringUntill.reverseLetters("!a@b#c$"));
    }
    @Test
    void reversesOnlyLetters() {
        assertEquals("dcba", StringUntill.reverseLetters("abcd"));
    }
    @Test
    void shouldPreserveCaseWhenReversing() {
        assertEquals("A@b", StringUntill.reverseLetters("b@A"));
    }
    @Test
    void shouldReturnNullForNullInput() {
        assertNull(StringUntill.reverseLetters(null));
    }
}

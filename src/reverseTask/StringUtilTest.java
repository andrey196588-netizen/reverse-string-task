package reverseTask;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtilTest {

    @Test
    @DisplayName("shouldReverseStandardString — Разворот стандартной строки с символами")
    void shouldReverseStandardString() {
        assertEquals("t@eb eht av$J!123", StringUntill.reverseLetters("J@va the be$t!123"));
    }
    @Test
    @DisplayName("shouldReturnEmptyForEmptyInput — Возврат пустой строки при пустом вводе")
    void shouldReturnEmptyForEmptyInput() {
        assertEquals("", StringUntill.reverseLetters(""));
    }
    @Test
    @DisplayName("shouldHandleSingleLetter — Обработка строки из одной буквы")
    void shouldHandleSingleLetter() {
        assertEquals("a", StringUntill.reverseLetters("a"));
    }
    @Test
    @DisplayName("shouldKeepNonLettersInPlace — Сохранение спецсимволов и цифр на местах")
    void shouldKeepNonLettersInPlace() {
        assertEquals("123 @#!", StringUntill.reverseLetters("123 @#!"));
        assertEquals("!c@b#a$", StringUntill.reverseLetters("!a@b#c$"));
    }
    @Test
    @DisplayName("shouldReverseOnlyLetters — Реверс строки, состоящей только из букв")
    void shouldReverseOnlyLetters() {
        assertEquals("dcba", StringUntill.reverseLetters("abcd"));
    }
    @Test
    @DisplayName("shouldPreserveCaseWhenReversing — Сохранение регистра букв")
    void shouldPreserveCaseWhenReversing() {
        assertEquals("A@b", StringUntill.reverseLetters("b@A"));
    }
    @Test
    @DisplayName("shouldReturnNullForNullInput — Возврат null при null на входе")
    void shouldReturnNullForNullInput() {
        assertNull(StringUntill.reverseLetters(null));
    }
}

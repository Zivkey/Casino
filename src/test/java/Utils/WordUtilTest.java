package Utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class WordUtilTest {

    @Test
    void checkLettersWord() {
        Assertions.assertEquals(true, WordUtil.checkLetters("Marko"));
    }

    @Test
    void checkLettersFalse() {
        Assertions.assertEquals(false, WordUtil.checkLetters("M4KA2./'1-"));
    }

    @Test
    void checkLettersEmpty() {
        Assertions.assertEquals(false, WordUtil.checkLetters(""));
    }

    @Test
    void onlyNumbersNumber() {
        Assertions.assertEquals(true, WordUtil.onlyNumbers("321312"));
    }

    @Test
    void onlyNumbersFalse() {
        Assertions.assertEquals(false, WordUtil.onlyNumbers("Per12a"));
    }

    @Test
    void onlyNumbersEmpty() {
        Assertions.assertEquals(false, WordUtil.onlyNumbers(""));
    }
}
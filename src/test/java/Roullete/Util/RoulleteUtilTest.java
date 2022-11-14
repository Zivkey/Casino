package Roullete.Util;

import javafx.scene.control.TextField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoulleteUtilTest {

    @Test
    void winningNumber() {
        Assertions.assertEquals(20, RoulleteUtil.winningNumber(400));
    }
    @Test
    void winningNumberZero() {
        Assertions.assertEquals(0, RoulleteUtil.winningNumber(0));
    }

    @Test
    void winningNumberNegative() {
        Assertions.assertEquals(-10, RoulleteUtil.winningNumber(-200));
    }

    @Test
    void whichTwelve() {
        Assertions.assertEquals(2, RoulleteUtil.whichTwelve(22));
    }

    @Test
    void whichTwelveZero() {
        Assertions.assertEquals(0, RoulleteUtil.whichTwelve(0));
    }

    @Test
    void whichTwelveNegative() {
        Assertions.assertEquals(0, RoulleteUtil.whichTwelve(-31));
    }

    @Test
    void checkInput() {
        TextField textField = new TextField();
        textField.setText("123");
        Assertions.assertEquals(0, RoulleteUtil.checkInput(textField));
    }
    @Test
    void checkInputWords() {
        TextField textField = new TextField();
        textField.setText("asd");
        Assertions.assertEquals(0, RoulleteUtil.checkInput(textField));
    }

    @Test
    void checkInputNull() {
        TextField textField = new TextField();
        textField.setText("");
        Assertions.assertEquals(0, RoulleteUtil.checkInput(textField));
    }

}
package Roullete.Util;

import Utils.WordUtil;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;


public class RoulleteUtil {
    /**
     * Metoda koja uzima poziciju loptice i proverava na koji je broj stala
     * @param position
     * @return broj koji je najblizi loptici
     */
    public static int winningNumber(double position) {
        int numbersTraveled = (int) position / 20;
        return numbersTraveled;
    }

    /**
     * Metoda koja proverava koju smo trecinu odabrali da igramo
     * @param r1
     * @param r2
     * @param r3
     * @return Koju trecinu igramo
     */
    public static int whichTwelveIsSelected(RadioButton r1, RadioButton r2, RadioButton r3) {
        if (r1.isSelected()) {
            return 1;
        } else if (r2.isSelected()) {
            return 2;
        } else if (r3.isSelected()) {
            return 3;
        } else {
            return 0;
        }
    }

    /**
     * Metoda koja proverava u koju trecinu je pala loptica
     * @param winningNumber
     * @return trecinu u kojoj je pala loptica
     */
    public static int whichTwelve(int winningNumber) {
        if (winningNumber > 0 && winningNumber < 13) {
            return 1;
        } else if (winningNumber > 12 && winningNumber < 25) {
            return 2;
        } else if (winningNumber > 24 && winningNumber < 37) {
            return 3;
        } else {
            return 0;
        }
    }

    /**
     * Metoda koja proverava da li je input broj
     * @param textField
     * @return ili broj ili u losem inputu nulu
     */
    public static int checkInput(TextField textField) {
        if (textField.getText() != "" && WordUtil.onlyNumbers(textField.getText())) {
            return Integer.parseInt(textField.getText());
        } else {
            return 0;
        }
    }


}

package Utils;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

import java.util.List;

public class StyleUtil {

    /**
     * Uzima sve lejble i menja im font
     * @param labels
     */
    public static void fontLabel(List<Label> labels) {
        Font font = new Font("Verdana", 14);
        for (Label l : labels) {
            l.setFont(font);
        }
    }

    /**
     * Uzima sve dugmice i menja im font i pozadinu
     * @param buttons
     */
    public static void buttonStyle(List<Button> buttons) {
        Font font = new Font("Verdana", 16);
        for (Button b : buttons) {
            b.setFont(font);
            b.setStyle("-fx-background-color: #c742ea");
        }
    }

    /**
     * Uzima sve textfildove i menja im font
     * @param textFields
     */
    public static void fontFields(List<TextField> textFields) {
        Font font = new Font("Verdana", 14);
        for (TextField field : textFields) {
            field.setFont(font);
        }
    }
}

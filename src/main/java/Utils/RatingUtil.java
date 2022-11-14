package Utils;

import Classes.CurrentUser;
import Classes.Rating;
import Database.RatingController;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;

import java.util.List;

public class RatingUtil {

    /**
     * Metoda koja proverava da li je trenutni user vec ostavio recenziju za aplikaciju
     * Ako jeste onda stavlja ocenu i poruku koju je on vec ostavio
     * @param textArea
     * @param one
     * @param two
     * @param three
     * @param four
     * @param five
     */
    public static void checkRating(TextArea textArea, RadioButton one, RadioButton two,
                                   RadioButton three, RadioButton four, RadioButton five) {
        List<Rating> ratingList = RatingController.readRatings();
        if (ratingList != null) {
            for (Rating rating : ratingList) {
                if (rating.getId() == CurrentUser.getCurrentUsedId()) {
                    textArea.setText(rating.getMessage());
                    switch (rating.getRating()) {
                        case 1:
                            one.setSelected(true);
                            break;
                        case 2:
                            two.setSelected(true);
                            break;
                        case 3:
                            three.setSelected(true);
                            break;
                        case 4:
                            four.setSelected(true);
                            break;
                        case 5:
                            five.setSelected(true);
                            break;
                    }
                }
            }
        }
    }


    /**
     * Proverava da li trenutni user vec ima svoju recenziju ili mu je prvi put da ocenju aplikaciju
     * @return 1 ili 0 u odnosu na to da li vec postoji recenzija od usera
     */
    public static int newRating() {
        List<Rating> ratingList = RatingController.readRatings();
        if (ratingList != null) {
            for (Rating rating : ratingList) {
                if (rating.getId() == CurrentUser.getCurrentUsedId()) {
                    return 1;
                }
            }
        }
        return 0;
    }
}

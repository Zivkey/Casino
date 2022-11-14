package Utils;

public class WordUtil {

    /**
     * Proverava da li je rec sastavljena od samo slova
     * @param word
     * @return da li rec sadrzi samo slova
     */
    public static boolean checkLetters(String word) {
        char [] array = word.toCharArray();
        if (array.length == 0) {
            return false;
        }
        for (char character : array) {
            if (!Character.isLetter(character)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Proverava da li je rec ststavljena od samo brojeva
     * @param word
     * @return da li rec sadrzi samo brojeve
     */
    public static boolean onlyNumbers(String word) {
        char [] array = word.toCharArray();
        if (array.length == 0) {
            return false;
        }
        for (char character : array) {
            if (!Character.isDigit(character)) {
                return false;
            }
        }
        return true;
    }
}

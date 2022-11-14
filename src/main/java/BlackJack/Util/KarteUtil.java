package BlackJack.Util;

import BlackJack.Classes.Card;
import BlackJack.Classes.Symbol;

import java.util.ArrayList;
import java.util.List;

public class KarteUtil {
    /**
     * Metoda koja pravi spil sa kartama koji je iste velicine kao i pravi spil
     * @return vraca listu karata koja predstavlja jedan spil u igri
     */
    public static List<Card> napraviSpil() {
        List<Card> spil = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            for (int j = 0; j < Symbol.values().length; j++) {
                Symbol symbol = Symbol.values()[j];
                Card card = new Card(symbol, i);
                spil.add(card);
            }
        }
        for (int i = 12; i < 15; i++) {
            for (int j = 0; j < Symbol.values().length; j++) {
                Symbol symbol = Symbol.values()[j];
                Card card = new Card(symbol, i);
                spil.add(card);
            }
        }
        return spil;
    }
}

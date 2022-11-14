package BlackJack.Util;

import BlackJack.Classes.Card;
import BlackJack.Classes.Symbol;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BlackjackUtilTest {

    @Test
    void cardValueOne() {
        Card c1 = new Card(Symbol.DIAMOND, 1);
        List<Card> cards = new ArrayList<>();
        cards.add(c1);
        Assertions.assertEquals(11, BlackjackUtil.cardValue(cards));
    }

    @Test
    void cardValueHigh() {
        Card c1 = new Card(Symbol.DIAMOND, 14);
        List<Card> cards = new ArrayList<>();
        cards.add(c1);
        Assertions.assertEquals(10, BlackjackUtil.cardValue(cards));
    }
    @Test
    void cardValueNormal() {
        Card c1 = new Card(Symbol.DIAMOND, 7);
        List<Card> cards = new ArrayList<>();
        cards.add(c1);
        Assertions.assertEquals(7, BlackjackUtil.cardValue(cards));
    }
}
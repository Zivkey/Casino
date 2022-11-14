package BlackJack.Util;

import BlackJack.Classes.Card;
import BlackJack.Classes.Symbol;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class BlackjackUtil {
    /**
     * Metoda koja pri zavrsetku igranja dilera uzima broj karata od dilera i uporedjuje ga sa brojem karata koje ima igrac
     * Ona u odnosu na to menja label
     * @param igrac
     * @param dealer
     * @param result
     * @param currentBet
     */
    public static void checkWinner(Label igrac, Label dealer, Label result, int currentBet) {
        if (igrac.getText() == "BUST!!!!") {
            result.setText("Dealer is the winner! You lost: " + currentBet);
            result.setTextFill(Color.RED);
        } else if (dealer.getText() == "BUST!!!!") {
            result.setText("Player is the winner! You won: " + (currentBet * 2));
            result.setTextFill(Color.GREEN);
        } else if (Integer.parseInt(igrac.getText()) > Integer.parseInt(dealer.getText())) {
            result.setText("Player is the winner! You won: " + (currentBet * 2));
            result.setTextFill(Color.GREEN);
        } else if (Integer.parseInt(igrac.getText()) < Integer.parseInt(dealer.getText())) {
            result.setText("Dealer is the winner! You lost:" + currentBet);
            result.setTextFill(Color.RED);
        } else {
            result.setText("The has been a draw! Start your new bet:");
            result.setTextFill(Color.BLACK);
        }
    }

    /**
     * Metoda koja menja jacinu karata ako je stih u 10 a ako je 1 u 11
     * @param spil
     * @return
     */
    public static int cardValue(List<Card> spil) {
        Card card = spil.get(spil.size() - 1);
        if (card.getNumber() == 1) {
            return 11;
        }
        else if (card.getNumber() > 9) {
            return 10;
        } else {
            return card.getNumber();
        }
    }

    /**
     * Metoda koja simulira izvlacenje jedne karte
     * Ona uzima prvu kartu iz spila i pravi novi StackPane koji sadrzi vrednosti te izvucene karte
     * @param spil
     * @return StackPane karte koja je izvucena
     */
    public static StackPane drawACard(List<Card> spil) {
        StackPane karta = new StackPane();
        Rectangle rectangle = new Rectangle(75, 100);
        Card card1 = spil.get(spil.size() - 1);
        spil.remove(spil.size() - 1);
        int kartaBroj = card1.getNumber();
        Label broj = new Label();
        switch (kartaBroj) {
            case 12:
                broj.setText(("JACK \n" + card1.getSymbol()));
                break;
            case 13:
                broj.setText(("QUEEN \n" + card1.getSymbol()));
                break;
            case 14:
                broj.setText(("KING \n" + card1.getSymbol()));
                break;
            default:
                broj.setText((card1.getNumber())+ " " + card1.getSymbol());

        }
        broj.setTextFill(Color.WHITE);
        broj.setStyle("-fx-font-weight: bold");
        if (card1.getSymbol() == Symbol.DIAMOND || card1.getSymbol() == Symbol.HEART) {
            rectangle.setFill(Color.RED);
        } else {
            rectangle.setFill(Color.BLACK);
        }

        karta.getChildren().addAll(rectangle, broj);
        return karta;
    }

    /**
     * Metoda koja se aktivira pri povetku igre ona simulira izvlacenje dve karte od stranje igraca
     * koje se dodaju u listu StackPaneova i njihova vrednost se dodaje igracu
     * @param spil
     * @param playerCount
     * @param one
     * @return listu od dve karte koje igrac izvlaci na pocetku igre
     */
    public static List<StackPane> playerDraw(List<Card> spil, Label playerCount, Label one) {
        List<StackPane> kartice = new ArrayList<>();
        int prva = cardValue(spil);
        StackPane kartica1 = drawACard(spil);
        kartice.add(kartica1);
        int druga = cardValue(spil);
        StackPane kartica2 = drawACard(spil);
        kartice.add(kartica2);
        if (prva == 11 || druga == 11) {
            one.setText("1");
        } else if (prva == 11 && druga == 11) {
            one.setText("2");
            oneAndEleven(playerCount, one);
        }
        playerCount.setText(String.valueOf(prva + druga));
        return kartice;
    }

    /**
     * Metoda koja saktriva drugu kartu od dilera tako sto stavlja plavi pravougaonik preko nje
     * @param karta
     * @return Sakrivenu kartu
     */
    public static StackPane hiddenCard(StackPane karta) {
        Rectangle rectangle = new Rectangle(75, 100);
        rectangle.setFill(Color.BLUE);
        karta.getChildren().add(rectangle);
        return karta;
    }

    /**
     * Metoda koja uzima skrivenu kartu od dilera i sklanja plavi pravougaonik sa nje i otkriva je
     * zatim je zapisuje dileru u njegov count
     * @param reveal
     * @param hidden
     * @param dealerCount
     */
    public static void revealCard(StackPane reveal, Label hidden, Label dealerCount) {
        reveal.getChildren().remove(2);
        int current = Integer.parseInt(dealerCount.getText());
        int hiddenValue = Integer.parseInt(hidden.getText());
        dealerCount.setText(String.valueOf(current + hiddenValue));
    }

    /**
     * Metoda koja simulira izvlacenje prve dve karte od dilera od kojih se jedna stavlja
     * da bude neotkrivena movocu prethodne metode i dodaje u count njegov broj
     * @param dealerbox
     * @param spil
     * @param dealerCount
     * @param hidden
     * @param one
     * @return Hbox koji sadrzi paneove dilerove dve izvucene karte
     */
    public static HBox dealerDrawing(HBox dealerbox, List<Card> spil, Label dealerCount, Label hidden, Label one) {
        int prva = BlackjackUtil.cardValue(spil);
        StackPane karta1 = drawACard(spil);
        dealerCount.setText(String.valueOf(prva));
        int druga = BlackjackUtil.cardValue(spil);
        StackPane karta2 = drawACard(spil);
        hidden.setText(String.valueOf(druga));
        karta2 = hiddenCard(karta2);
        dealerbox.getChildren().addAll(karta1, karta2);
        if (prva == 11 || druga == 11) {
            one.setText("1");
        } else if (prva == 11 && druga == 11) {
            one.setText("2");
        }
        return dealerbox;
    }

    /**
     * Metoda koja simulira delorovo izvlacenje ostatka karata
     * diler ce uvek da izvlaci novu kartu dok je njegov count manji od 17 i onda ce prestati
     * metoda sve karte koje on izvuce stavlja u jedan Hbox
     * @param dealerbox
     * @param spil
     * @param dealerLaber
     * @param ones
     * @return hbox sa paneovima svih dilerovih karata koje je on izvukao
     */
    public static HBox dealerRestOfTheDraw(HBox dealerbox, List<Card> spil, Label dealerLaber, Label ones) {
        int dealerCount = Integer.parseInt(dealerLaber.getText());
        while (dealerCount < 17) {
            int brojKartice = cardValue(spil);
            StackPane kartica = drawACard(spil);
            dealerbox.getChildren().add(kartica);
            dealerCount += brojKartice;
        }
        if (dealerCount > 21 && Integer.parseInt(ones.getText()) == 1 || Integer.parseInt(ones.getText()) == 2) {
            oneAndEleven(dealerLaber, ones);
        }
        else if (dealerCount > 21) {
            dealerLaber.setTextFill(Color.RED);
            dealerLaber.setText("BUST!!!!");
        } else {
            dealerLaber.setText(String.valueOf(dealerCount));
        }
        return dealerbox;
    }

    /**
     * Metoda koja uzima label!!! i proverava broj jedinica od dilera i broj jedinica od igraca
     * jedinica se gleda kao 11 medjutim ako se predje broj 21 onda se jedinica pretvara u broj 1
     * i moze se nastaviti izvlacenje karata
     * @param count
     * @param ones
     */
    public static void oneAndEleven(Label count, Label ones) {
        if (Integer.parseInt(ones.getText()) == 1 || Integer.parseInt(ones.getText()) == 2) {
            int maxCount = Integer.parseInt(count.getText());
            if (maxCount > 21) {
                count.setText(String.valueOf(maxCount - 10));
            }
            int newvalue = Integer.parseInt(ones.getText()) - 1;
            ones.setText(String.valueOf(newvalue));
        }
    }

}

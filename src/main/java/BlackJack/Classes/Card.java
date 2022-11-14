package BlackJack.Classes;

/**
 * Klasa karta koja sadrzi znak i broj i simulira pravu kartu
 */
public class Card {
    private Symbol symbol;
    private int number;

    public Card() {
    }

    public Card(Symbol symbol, int broj) {
        this.symbol = symbol;
        this.number = broj;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Karta{" +
                "znak=" + symbol +
                ", broj=" + number +
                '}';
    }
}

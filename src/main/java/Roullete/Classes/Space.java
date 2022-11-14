package Roullete.Classes;

/**
 * Klasa polje koja sadrzi broj i boju i pomocu koje se prave polja za igru
 */
public class Space {
    private int number;
    private SpaceColor spaceColor;

    public Space() {
    }

    public Space(int number, SpaceColor spaceColor) {
        this.number = number;
        this.spaceColor = spaceColor;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public SpaceColor getSpaceColor() {
        return spaceColor;
    }

    public void setSpaceColor(SpaceColor spaceColor) {
        this.spaceColor = spaceColor;
    }

    @Override
    public String toString() {
        return "Space{" +
                "number=" + number +
                ", spaceColor=" + spaceColor +
                '}';
    }
}

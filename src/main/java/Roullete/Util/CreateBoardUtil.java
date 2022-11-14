package Roullete.Util;

import Roullete.Classes.Space;
import Roullete.Classes.SpaceColor;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class CreateBoardUtil {
    /**
     * Metoda koja pravi 37 polja sa brojevima i bojama za rulet
     * @return listu svih polja
     */
    public static List<Space> createSpaces() {
        List<Space> listOfSpaces = new ArrayList<>();
        for (int i = 0; i < 37; i++) {
            if (i == 0) {
                Space space = new Space(i, SpaceColor.GREEN);
                listOfSpaces.add(space);
            } else if (i % 2 == 0) {
                Space space = new Space(i, SpaceColor.RED);
                listOfSpaces.add(space);
            } else {
                Space space = new Space(i, SpaceColor.BLACK);
                listOfSpaces.add(space);
            }
        }
        return listOfSpaces;
    }

    /**
     * Metoda koja od napravljenih polja pravi StackPaneove koji ce se prikazivati na stranici
     * @param listOfSpaces
     * @return Listu Paneova koji ce predstavljati polja
     */
    public static List<StackPane> createPanes(List<Space> listOfSpaces) {
        List<StackPane> listOfPanes = new ArrayList<>();

        for (Space space : listOfSpaces) {
            Rectangle rectangle = new Rectangle(20,30);
            if (space.getSpaceColor() == SpaceColor.GREEN) {
                rectangle.setFill(Color.GREEN);
            }
            else if (space.getSpaceColor() == SpaceColor.BLACK) {
                rectangle.setFill(Color.BLACK);
            } else {
                rectangle.setFill(Color.RED);
            }
            StackPane stackPane = new StackPane();
            Label number = new Label(String.valueOf(space.getNumber()));
            number.setTextFill(Color.WHITE);
            stackPane.getChildren().addAll(rectangle, number);
            listOfPanes.add(stackPane);
        }
        return listOfPanes;
    }

    /**
     * Metoda koja uzima sve Paneove koje smo napravili za polja i stavlja ih u jedan HBox
     * @param panes
     * @return Hbox koji sadrzi sve paneove
     */
    public static HBox createBoard(List<StackPane> panes) {
        HBox up = new HBox();
        up.setPadding(new Insets(30, 0 , 0, 0));
        List<StackPane> board = CreateBoardUtil.createPanes(CreateBoardUtil.createSpaces());
        for (StackPane pane : board) {
            up.getChildren().add(pane);
        }
        return up;
    }


}

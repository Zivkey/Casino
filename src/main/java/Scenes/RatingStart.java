package Scenes;

import Classes.CurrentUser;
import Classes.Rating;
import Database.RatingController;
import Utils.RatingUtil;
import Utils.StyleUtil;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

public class RatingStart extends Application {
    private Label header = new Label("Please leave us a rating");
    private Label errorMessage = new Label(" ");


    private RadioButton one = new RadioButton("1");
    private RadioButton two = new RadioButton("2");
    private RadioButton three = new RadioButton("3");
    private RadioButton four = new RadioButton("4");
    private RadioButton five = new RadioButton("5");
    private ToggleGroup buttonGroup = new ToggleGroup();
    private TextArea textArea = new TextArea();

    private Button ratingButton = new Button("Finish");
    private Button backButton = new Button("Back to menu");


    @Override
    public void start(Stage stage) throws Exception {
        // Fonts

        Font font = new Font("Verdana", 14);
        List<Label> labels = Arrays.asList(header, errorMessage);
        List<Button> buttonsStyle = Arrays.asList(backButton, ratingButton);
        StyleUtil.buttonStyle(buttonsStyle);
        StyleUtil.fontLabel(labels);
        textArea.setFont(font);
        RatingUtil.checkRating(textArea, one, two, three, four, five);
        List<RadioButton> buttons = Arrays.asList(one, two, three, four, five);
        for (RadioButton button : buttons) {
            button.setToggleGroup(buttonGroup);
            button.setFont(font);
        }
        textArea.setWrapText(true);

        // Boxes

        HBox buttonBox = new HBox(one, two, three, four, five);
        buttonBox.setSpacing(10);
        buttonBox.setAlignment(Pos.CENTER);

        // Root

        VBox root = new VBox(header, buttonBox, errorMessage, textArea, ratingButton, backButton);
        root.setSpacing(30);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color:#eebddc");

        // Scene

        Scene scene = new Scene(root, 400, 400);
        stage.setTitle("Rating");
        stage.setScene(scene);
        stage.show();

        // LAMBDA

        backButton.setOnAction(actionEvent -> {
            stage.close();
            try {
                new MainMenuStart().start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        ratingButton.setOnAction(actionEvent -> {
            if (buttonGroup.getSelectedToggle() != null) {
                int newRating = RatingUtil.newRating();
                errorMessage.setText("Thank you for your rating!");
                RadioButton selected = (RadioButton) buttonGroup.getSelectedToggle();
                int ratingInt = Integer.parseInt(selected.getText());
                String message;
                if (textArea.getText() != null) {
                    message = textArea.getText();
                } else {
                    message = "/";
                }
                Rating rating = new Rating(CurrentUser.getCurrentUsedId(), CurrentUser.getCurrentUserName(), ratingInt, message);
                System.out.println(rating);
                if (newRating == 0) {
                    RatingController.createRating(rating);
                } else {
                    RatingController.updateRating(rating);
                }
            } else {
                errorMessage.setText("You need to select the rating!");
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}

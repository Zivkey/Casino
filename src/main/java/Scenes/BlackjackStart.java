package Scenes;

import BlackJack.Classes.Card;
import BlackJack.Util.BlackjackUtil;
import BlackJack.Util.KarteUtil;
import Classes.CurrentUser;
import Database.UserController;
import Utils.StyleUtil;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// RADI ALI JE UZASNO MORAS SVE OD POCETKA MAJMUNCINO RETARDIRANA!

public class BlackjackStart extends Application {

    private Button hit = new Button("Hit");
    private Button stand = new Button("Stand");
    private Button start = new Button("START");
    private Label playerText = new Label("Your current card value is: ");
    private Label playerCount = new Label("0");
    private Label dealerText = new Label("Dealer current card value is: ");
    private Label dealerCount = new Label("0");
    private Label hiddenCard = new Label("");
    private Label winner = new Label("");
    private Label playerOnes = new Label("0");
    private Label dealerOnes = new Label("0");
    private HBox dealerbox = new HBox();
    private Button backButton = new Button("Back to menu");
    private Label currentLabel = new Label();
    private Label balanceLabel = new Label();

    // Bottom ui
    private Label amountLabel = new Label("Amount:");
    private TextField amountField = new TextField();

    @Override
    public void start(Stage stage) throws IOException {

        Font font = new Font("Verdana", 14);

        // Style

        List<Label> labelList = Arrays.asList(playerText, playerCount, dealerText, dealerCount, winner, playerOnes, dealerOnes, currentLabel, balanceLabel, amountLabel);
        List<Button> buttonList = Arrays.asList(hit, stand, start, backButton);
        StyleUtil.buttonStyle(buttonList);
        StyleUtil.fontLabel(labelList);

        // Player board

        VBox igrac = new VBox();
        igrac.setSpacing(20);
        igrac.setPadding(new Insets(20));
        HBox box1 = new HBox(hit, stand, start);
        HBox box2 = new HBox();
        HBox box3 = new HBox(playerText, playerCount);
        igrac.getChildren().addAll(box1, box2, box3);
        box1.setSpacing(20);
        box2.setSpacing(20);
        List<Card> spil = KarteUtil.napraviSpil();
        Collections.shuffle(spil);
        box1.setAlignment(Pos.CENTER);
        box2.setAlignment(Pos.CENTER);
        box3.setAlignment(Pos.CENTER);

        // Dealer board

        VBox dealer = new VBox();
        dealer.setSpacing(20);
        dealer.setPadding(new Insets(20));
        HBox dealerBox1 = new HBox(dealerText, dealerCount);
        dealerbox.setSpacing(20);
        dealerBox1.setSpacing(20);
        dealer.getChildren().add(dealerBox1);
        dealer.getChildren().add(dealerbox);
        dealer.setAlignment(Pos.CENTER);
        dealerbox.setAlignment(Pos.CENTER);
        dealerBox1.setAlignment(Pos.CENTER);

        // Bottom ui

        VBox ui = new VBox();
        HBox winnerBox = new HBox(winner);
        winner.setText("Insert your bet:");
        ui.getChildren().addAll(amountLabel, amountField, winnerBox);
        winnerBox.setSpacing(20);
        ui.setSpacing(20);
        ui.setPadding(new Insets(20));
        amountField.setMaxWidth(200);
        winnerBox.setAlignment(Pos.CENTER);
        ui.setAlignment(Pos.CENTER);


        // Current player and balance

        HBox currentBox = new HBox(currentLabel, balanceLabel);
        currentLabel.setText("Username: " + CurrentUser.getCurrentUserName());
        balanceLabel.setText("Balance: " + CurrentUser.getCurrentUserBalance());
        currentBox.setAlignment(Pos.BASELINE_RIGHT);
        currentBox.setSpacing(20);
        currentBox.setPadding(new Insets(20));
        currentBox.setAlignment(Pos.CENTER);

        // Root and scene

        VBox root = new VBox(dealer, igrac, ui, currentBox, backButton);
        root.setStyle("-fx-background-color:#eebddc");
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 600, 700);
        stage.setTitle("BlackJack");
        stage.setScene(scene);
        stage.show();

        // Hit event

        EventHandler<ActionEvent> hitEvent = actionEvent -> {
            int cardValue = Integer.parseInt(playerCount.getText());
            int nextCardValue = BlackjackUtil.cardValue(spil);
            int newValue = nextCardValue + cardValue;
            StackPane kartica = BlackjackUtil.drawACard(spil);
            box2.getChildren().add(kartica);
            playerCount.setText(String.valueOf(newValue));
            if (nextCardValue == 11) {
                int oldText = Integer.parseInt(playerOnes.getText());
                playerOnes.setText(String.valueOf(oldText + 1));
            }
            if (Integer.parseInt(playerCount.getText()) > 21 && Integer.parseInt(playerOnes.getText()) > 0) {
                BlackjackUtil.oneAndEleven(playerCount, playerOnes);
            }
            else if (Integer.parseInt(playerCount.getText()) > 21) {
                playerCount.setTextFill(Color.RED);
                playerCount.setText("BUST!!!!");
                BlackjackUtil.revealCard((StackPane) dealerbox.getChildren().get(1), hiddenCard, dealerCount);
                BlackjackUtil.checkWinner(playerCount, dealerCount, winner, Integer.parseInt(amountField.getText()));
                hit.setOnAction(null);
                stand.setOnAction(null);
                spil.clear();
                playerOnes.setText("0");
                dealerOnes.setText("0");
                balanceLabel.setText("Balance: " + CurrentUser.getCurrentUserBalance());
            }
        };


        // Stand event

        EventHandler<ActionEvent> standEvent = actionEvent -> {
            BlackjackUtil.revealCard((StackPane) dealerbox.getChildren().get(1), hiddenCard, dealerCount);
            dealer.getChildren().remove(dealerbox);
            dealer.getChildren().add(BlackjackUtil.dealerRestOfTheDraw(dealerbox, spil, dealerCount, dealerOnes));
            BlackjackUtil.checkWinner(playerCount, dealerCount, winner, Integer.parseInt(amountField.getText()));
            if (dealerCount.getText() == "BUST!!!!") {
                CurrentUser.changeUserBalance(CurrentUser.getCurrentUserBalance() + 2 * Integer.parseInt(amountField.getText()));
                UserController.updateBalance(CurrentUser.getCurrentUsedId(), CurrentUser.getCurrentUserBalance());
            } else if (Integer.parseInt(dealerCount.getText()) < Integer.parseInt(playerCount.getText())) {
                CurrentUser.changeUserBalance(CurrentUser.getCurrentUserBalance() + 2 * Integer.parseInt(amountField.getText()));
                UserController.updateBalance(CurrentUser.getCurrentUsedId(), CurrentUser.getCurrentUserBalance());
            } else if (Integer.parseInt(dealerCount.getText()) == Integer.parseInt(playerCount.getText())) {
                CurrentUser.changeUserBalance(CurrentUser.getCurrentUserBalance() + Integer.parseInt(amountField.getText()));
                UserController.updateBalance(CurrentUser.getCurrentUsedId(), CurrentUser.getCurrentUserBalance());
            }
            hit.setOnAction(null);
            stand.setOnAction(null);
            spil.clear();
            playerOnes.setText("0");
            dealerOnes.setText("0");
            balanceLabel.setText("Balance: " + CurrentUser.getCurrentUserBalance());
        };

        // Start event

        EventHandler<ActionEvent> startEvent = actionEvent -> {
            if (amountField.getText() == "") {
                winner.setText("Insert amount:");
            }
            else if (Integer.parseInt(amountField.getText()) > CurrentUser.getCurrentUserBalance()) {
                winner.setText("Insufficient amount:");
            } else {
                CurrentUser.changeUserBalance(CurrentUser.getCurrentUserBalance() - Integer.parseInt(amountField.getText()));
                UserController.updateBalance(CurrentUser.getCurrentUsedId(), CurrentUser.getCurrentUserBalance());
                dealerCount.setTextFill(Color.BLACK);
                playerCount.setTextFill(Color.BLACK);
                spil.addAll(KarteUtil.napraviSpil());
                Collections.shuffle(spil);
                winner.setText("");
                box2.getChildren().clear();
                dealerbox.getChildren().clear();
                dealerbox = BlackjackUtil.dealerDrawing(dealerbox, spil, dealerCount, hiddenCard, dealerOnes);
                List<StackPane> kartice = BlackjackUtil.playerDraw(spil, playerCount, playerOnes);
                box2.getChildren().addAll(kartice);
                hit.setOnAction(hitEvent);
                stand.setOnAction(standEvent);
                balanceLabel.setText("Balance: " + CurrentUser.getCurrentUserBalance());
            }
        };

        backButton.setOnAction(actionEvent -> {
            stage.close();
            try {
                new MainMenuStart().start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        amountField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")  || newValue == "") {
                    amountField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });



        hit.setOnAction(null);
        start.setOnAction(startEvent);
        stand.setOnAction(null);

    }

    public static void main(String[] args) {
        launch();
    }
}
package Scenes;

import Classes.CurrentUser;
import Classes.Withdraw;
import Database.UserController;
import Database.WithdrawController;
import Exceptions.WithdrawException;
import Utils.StyleUtil;
import Utils.WordUtil;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class WithdrawStart extends Application {

    private Label currentUserLabel = new Label();
    private Label currentAmountLabel = new Label();
    private Label howMuchLabel = new Label("How much do you wish to withdraw?");
    private TextField insertAmount = new TextField();
    private Label messageLabel = new Label();
    private Button withdrawButton = new Button("Withdraw");
    private Button backButton = new Button("Back to menu");


    @Override
    public void start(Stage stage) throws IOException {

        // Font

        Font font = new Font("Verdana", 14);
        List<Label> labels = Arrays.asList(currentAmountLabel, currentUserLabel, howMuchLabel, messageLabel);
        List<Button> buttons = Arrays.asList(backButton, withdrawButton);
        StyleUtil.buttonStyle(buttons);
        StyleUtil.fontLabel(labels);
        backButton.setFont(font);

        // Setting up the labels

        currentUserLabel.setText("Hello " + CurrentUser.getCurrentUserName());
        currentAmountLabel.setText("Your current balance is: " + CurrentUser.getCurrentUserBalance());
        insertAmount.setMaxWidth(150);

        // Root

        VBox root = new VBox(currentUserLabel, currentAmountLabel, howMuchLabel, insertAmount, withdrawButton, messageLabel, backButton);
        root.setSpacing(30);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color:#eebddc");


        // Scenes

        Scene scene = new Scene(root, 350, 400);
        stage.setTitle("Withdraw");
        stage.setScene(scene);
        stage.show();

        // LAMBDA

        withdrawButton.setOnAction(actionEvent -> {
            if (!WordUtil.onlyNumbers(insertAmount.getText()) || insertAmount.getText() == "") {
                messageLabel.setText("You need to enter a number");
            } else if (Integer.parseInt(insertAmount.getText()) > CurrentUser.getCurrentUserBalance()) {
                messageLabel.setText("Insuficent balance");
            } else {
                int withdrawAmount = Integer.parseInt(insertAmount.getText());
                Withdraw withdraw = null;
                try {
                    withdraw = new Withdraw(CurrentUser.getCurrentUsedId(), withdrawAmount);
                } catch (WithdrawException e) {
                    throw new RuntimeException(e);
                }
                CurrentUser.changeUserBalance(CurrentUser.getCurrentUserBalance() - withdrawAmount);
                UserController.updateBalance(CurrentUser.getCurrentUsedId(), CurrentUser.getCurrentUserBalance());
                WithdrawController.createWithdraw(withdraw);
                currentAmountLabel.setText(String.valueOf(CurrentUser.getCurrentUserBalance()));
                messageLabel.setText("You have succesfuly withdrawn " + withdrawAmount);
                insertAmount.setText("");
            }
        });


        backButton.setOnAction(actionEvent -> {
            stage.close();
            try {
                new MainMenuStart().start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
}

    public static void main(String[] args) {
        launch();
    }
}
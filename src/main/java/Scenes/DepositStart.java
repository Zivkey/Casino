package Scenes;

import Classes.CurrentUser;
import Classes.Deposit;
import Classes.Exchange;
import Database.DepositCtonroller;
import Database.UserController;
import Exceptions.DepositException;
import Utils.JsoupUtil;
import Utils.StyleUtil;
import Utils.WordUtil;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DepositStart extends Application {

    private Label currentUserLabel = new Label();
    private Label currentAmountLabel = new Label();
    private TextField insertAmount = new TextField();
    private Button backButton = new Button("Back to menu");
    private ComboBox<String> valuesCombo = new ComboBox<>();
    private Label currentLabel = new Label(" ");
    private Label messageLabel = new Label(" ");
    private Button depositButton = new Button("Deposit");
    private Button depositRSD = new Button("Deposit RSD");

    @Override
    public void start(Stage stage) throws IOException {

        // Jsoup

        List<Double> exchangerateList = new ArrayList<>();
        exchangerateList.add(0.0);
        List<Exchange> exchangeList = JsoupUtil.readFromWebsite();
        for (Exchange exchange : exchangeList) {
            valuesCombo.getItems().add(exchange.getName());
        }

        // Apply font

        List<Label> labels = Arrays.asList(currentLabel, currentAmountLabel, currentLabel, messageLabel, currentUserLabel);
        List<Button> buttons = Arrays.asList(backButton, depositButton, depositRSD);
        List<TextField> fields = Arrays.asList(insertAmount);
        StyleUtil.fontLabel(labels);
        StyleUtil.buttonStyle(buttons);
        StyleUtil.fontFields(fields);

        // Boxes and labels

        currentUserLabel.setText("Hello " + CurrentUser.getCurrentUserName());
        currentAmountLabel.setText("Your current balance is: " + CurrentUser.getCurrentUserBalance());
        insertAmount.setMaxWidth(150);
        HBox buttonBox = new HBox(depositButton, depositRSD);
        buttonBox.setSpacing(20);
        buttonBox.setAlignment(Pos.CENTER);
        VBox root = new VBox(currentUserLabel, currentAmountLabel, valuesCombo, currentLabel, insertAmount, messageLabel, buttonBox, backButton);
        root.setSpacing(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color:#eebddc");
        Scene scene = new Scene(root, 350, 430);
        stage.setTitle("Deposit");
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

        valuesCombo.valueProperty().addListener((observableValue, stringSingleSelectionModel, t1) -> {
            for (Exchange ex : exchangeList) {
                if (ex.getName() == t1) {
                    currentLabel.setText(ex.getCode() + "  " + ex.getExchangeRate());
                    exchangerateList.clear();
                    exchangerateList.add(ex.getExchangeRate());
                }
            }
        });

        depositButton.setOnAction(actionEvent -> {
            if (!WordUtil.onlyNumbers(insertAmount.getText()) || insertAmount.getText() == "") {
                messageLabel.setText("You need to enter a number");
            }
            else {
                int depositAmount = Integer.parseInt(insertAmount.getText());
                int trueAmount = (int) ( depositAmount * exchangerateList.get(0));
                CurrentUser.changeUserBalance(CurrentUser.getCurrentUserBalance() + trueAmount);
                UserController.updateBalance(CurrentUser.getCurrentUsedId(), CurrentUser.getCurrentUserBalance());
                currentAmountLabel.setText(String.valueOf(CurrentUser.getCurrentUserBalance()));
                messageLabel.setText("You have deposited " + trueAmount );
                insertAmount.setText("");
                Deposit deposit = null;
                try {
                    deposit = new Deposit(CurrentUser.getCurrentUsedId(), trueAmount);
                } catch (DepositException e) {
                    throw new RuntimeException(e);
                }
                DepositCtonroller.createDeposit(deposit);
            }
        });

        depositRSD.setOnAction(actionEvent -> {
            if (!WordUtil.onlyNumbers(insertAmount.getText()) || insertAmount.getText() == "") {
                messageLabel.setText("You need to enter a number");
            }
            else {
                int depositAmount = Integer.parseInt(insertAmount.getText());
                CurrentUser.changeUserBalance(CurrentUser.getCurrentUserBalance() + depositAmount);
                UserController.updateBalance(CurrentUser.getCurrentUsedId(), CurrentUser.getCurrentUserBalance());
                currentAmountLabel.setText(String.valueOf(CurrentUser.getCurrentUserBalance()));
                messageLabel.setText("You have deposited " + depositAmount);
                insertAmount.setText("");
                Deposit deposit = null;
                try {
                    deposit = new Deposit(CurrentUser.getCurrentUsedId(), depositAmount);
                } catch (DepositException e) {
                    throw new RuntimeException(e);
                }
                DepositCtonroller.createDeposit(deposit);
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}

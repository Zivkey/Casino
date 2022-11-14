package Scenes;

import Classes.CurrentUser;
import Database.UserController;
import Roullete.Threads.FewSeconds;
import Roullete.Util.CreateBoardUtil;
import Roullete.Util.RoulleteUtil;
import Utils.StyleUtil;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RouletteStart extends Application {


    // Roullete

    private Circle ball = new Circle(10);
    private Path path = new Path();
    private PathTransition pathTransition = new PathTransition();

    // Buttons

    private Button start = new Button("Start");
    private Button returnToMeni = new Button("Back to menu");
    private Button collect = new Button("Collect");

    // Winner

    private Label winningNumberLabel = new Label("");
    private Label winningAmountLabel = new Label("");

    // Invests colors

    private Rectangle red = new Rectangle(50, 50);
    private Rectangle black = new Rectangle(50, 50);
    private Rectangle green = new Rectangle(50, 50);

    private Label investLabelBlack = new Label("Amount:");
    private TextField blackField = new TextField();

    private Label investLabelRed = new Label("Amount:");
    private TextField redField = new TextField();

    private Label investLabelGreen = new Label("Amount:");
    private TextField greenFields = new TextField();

    // Twelves

    private Label investTwelvesLabel = new Label("Amount:");
    private TextField twelveFields = new TextField();

    private ToggleGroup twelvesToggleGroup = new ToggleGroup();
    private RadioButton firstTwelveSelect = new RadioButton("1st");
    private RadioButton secondTwelveSelect = new RadioButton("2nd");
    private RadioButton thirdTwelveSelect = new RadioButton("3rd");

    private Rectangle firstInvest = new Rectangle(45, 50);
    private Rectangle secondInvest = new Rectangle(45, 50);
    private Rectangle thirdInvest = new Rectangle(45, 50);

    // Number

    private Label chooseNumber = new Label("Number:");
    private Label investNumber = new Label("Amount:");
    private TextField numberField = new TextField();
    private TextField numberAmountField = new TextField();
    private Rectangle numberRectangle = new Rectangle(50, 50);
    private Label numberLabel = new Label("?");
    private StackPane numberpane = new StackPane(numberRectangle, numberLabel);

    // Twelves

    private Rectangle firstTwelve = new Rectangle(240, 10);
    private Rectangle secondTwelve = new Rectangle(240, 10);
    private Rectangle thirdTwelve = new Rectangle(240, 10);
    private Label firstTwelveLabel = new Label("1st Twelve");
    private Label secondTwelveLabel = new Label("2nd Twelve");
    private Label thirdTwelveLabel = new Label("3rd Twelve");

    // USER

    private Label userNameLabel = new Label("Username:");
    private Label currentUserLabel = new Label();
    private Label balanceUserLabel = new Label("Balance:");
    private Label currentBalanceLabel = new Label("");


    @Override
    public void start(Stage stage) throws IOException {

        // Style
        List<Button> buttonList = Arrays.asList(start, returnToMeni, collect);
        List<Label> labelList = Arrays.asList(winningNumberLabel, winningAmountLabel, investLabelBlack, investLabelRed, investLabelGreen, investTwelvesLabel, chooseNumber,
                investNumber, numberLabel, firstTwelveLabel, secondTwelveLabel, thirdTwelveLabel, userNameLabel, currentUserLabel, balanceUserLabel,currentBalanceLabel );
        StyleUtil.fontLabel(labelList);
        StyleUtil.buttonStyle(buttonList);

        //

        path.getElements().add(new MoveTo(10, 45));
        path.getElements().add(new CubicCurveTo(0, 45, 45, 45, 730, 45));
        path.setVisible(false);

        // Rullet Board

        Pane up = new Pane();
        HBox board = CreateBoardUtil.createBoard(CreateBoardUtil.createPanes(CreateBoardUtil.createSpaces()));
        up.getChildren().add(board);
        up.getChildren().add(path);
        up.getChildren().add(ball);
        ball.setVisible(false);
        ball.setFill(Color.LIGHTGRAY);
        List<Integer> results = new ArrayList<>();

        //First 12, second 12 and third 12

        VBox firstVbox = new VBox(firstTwelve, firstTwelveLabel);
        firstVbox.setPadding(new Insets(0, 0, 0, 20));
        VBox secondVbox = new VBox(secondTwelve, secondTwelveLabel);
        VBox thirdVbox = new VBox(thirdTwelve, thirdTwelveLabel);
        HBox twelves = new HBox(firstVbox, secondVbox, thirdVbox);
        firstVbox.setAlignment(Pos.CENTER);
        secondVbox.setAlignment(Pos.CENTER);
        thirdVbox.setAlignment(Pos.CENTER);
        firstTwelve.setFill(Color.TOMATO);
        secondTwelve.setFill(Color.CHOCOLATE);
        thirdTwelve.setFill(Color.GRAY);

        // Buttons Beneath the board

        HBox down = new HBox();
        down.getChildren().addAll(start, collect);
        collect.setDisable(true);
        down.setSpacing(20);
        down.setPadding(new Insets(20));
        down.setAlignment(Pos.CENTER);

        // Winner area

        HBox winner = new HBox(winningNumberLabel, winningAmountLabel);
        winner.setSpacing(20);
        winner.setAlignment(Pos.CENTER);
        winner.setPadding(new Insets(20));


        // Play Area for you to invest

        VBox blackInvest = new VBox(investLabelBlack, blackField, black);
        blackInvest.setSpacing(20);
        VBox redInvest = new VBox(investLabelRed, redField, red);
        redInvest.setSpacing(20);
        VBox greenInvest = new VBox(investLabelGreen, greenFields, green);
        greenInvest.setSpacing(20);

        // Twelves

        firstInvest.setFill(Color.TOMATO);
        secondInvest.setFill(Color.CHOCOLATE);
        thirdInvest.setFill(Color.GRAY);
        firstTwelveSelect.setToggleGroup(twelvesToggleGroup);
        secondTwelveSelect.setToggleGroup(twelvesToggleGroup);
        thirdTwelveSelect.setToggleGroup(twelvesToggleGroup);
        HBox twelveMerged = new HBox(firstInvest, secondInvest, thirdInvest);
        HBox twelveButtons = new HBox(firstTwelveSelect, secondTwelveSelect, thirdTwelveSelect);
        VBox twelveInvest = new VBox(investTwelvesLabel, twelveFields, twelveMerged, twelveButtons);
        twelveInvest.setSpacing(20);
        twelveButtons.setSpacing(10);
        twelveInvest.setAlignment(Pos.CENTER);

        // Number to invest:

        HBox numberAndAmountLabels = new HBox(investNumber, chooseNumber);
        HBox numberAndAmountFields = new HBox(numberAmountField, numberField);
        numberAmountField.setMinWidth(60);
        numberField.setMinWidth(60);
        VBox numberInvest = new VBox(numberAndAmountLabels, numberAndAmountFields, numberpane);
        numberLabel.setTextFill(Color.WHITE);
        numberLabel.setStyle("-fx-font-weight: bold");
        numberInvest.setAlignment(Pos.CENTER);
        numberInvest.setSpacing(30);
        numberAndAmountFields.setSpacing(10);
        numberAndAmountLabels.setSpacing(10);

        // InvestMerger

        HBox playArea = new HBox(blackInvest, redInvest, greenInvest, twelveInvest, numberInvest);
        playArea.setAlignment(Pos.CENTER);
        playArea.setSpacing(40);
        playArea.setPadding(new Insets(40));
        black.setFill(Color.BLACK);
        red.setFill(Color.RED);
        green.setFill(Color.GREEN);
        numberRectangle.setFill(Color.LIGHTGRAY);
        twelveFields.setMaxWidth(135);
        numberField.setMaxWidth(50);
        redField.setMaxWidth(50);
        blackField.setMaxWidth(50);
        greenFields.setMaxWidth(50);
        numberAmountField.setMaxWidth(50);

        // User and balance:

        HBox userBox = new HBox(userNameLabel, currentUserLabel, balanceUserLabel, currentBalanceLabel);
        currentUserLabel.setText(CurrentUser.getCurrentUserName());
        userBox.setSpacing(10);
        userBox.setPadding(new Insets(20));
        userBox.setAlignment(Pos.BASELINE_RIGHT);
        currentBalanceLabel.setText(String.valueOf(CurrentUser.getCurrentUserBalance()));


        // Whole stage

        VBox root = new VBox();
        root.setStyle("-fx-background-color:#eebddc");
        root.getChildren().addAll(up, twelves, down, winner, playArea, returnToMeni, userBox);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 740, 600);
        stage.setTitle("Roulette");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();


        // Return to meni button

        returnToMeni.setOnAction(actionEvent -> {
            stage.close();
            try {
                new MainMenuStart().start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        // Number field changer on input

        numberField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\d*") || newValue.equals("")) {
                numberField.setText(newValue.replaceAll("[^\\d]", ""));
                numberLabel.setText("?");
                numberRectangle.setFill(Color.LIGHTGRAY);
            } else if (Integer.parseInt(newValue) >= 0 && Integer.parseInt(newValue) < 38) {
                numberLabel.setText(newValue);
                if (Integer.parseInt(newValue) == 0) {
                    numberRectangle.setFill(Color.GREEN);
                } else if (Integer.parseInt(newValue) % 2 == 0) {
                    numberRectangle.setFill(Color.RED);
                } else {
                    numberRectangle.setFill(Color.BLACK);
                }

            } else {
                numberLabel.setText("?");
                numberRectangle.setFill(Color.LIGHTGRAY);
            }
        });

        // Start button

        start.setOnAction(actionEvent -> {
            winningNumberLabel.setText("");
            winningAmountLabel.setText("");
            int redAmount = RoulleteUtil.checkInput(redField);
            int blackAmount = RoulleteUtil.checkInput(blackField);
            int greenAmount = RoulleteUtil.checkInput(greenFields);
            int numberAmount = RoulleteUtil.checkInput(numberAmountField);
            int twelveAmount = RoulleteUtil.checkInput(twelveFields);
            if (twelveFields.getText() != "" && twelvesToggleGroup.getSelectedToggle() == null) {
                winningAmountLabel.setText("You need to select which twelve you want!");
            } else if (numberAmountField.getText() != "" && numberField.getText() == "") {
                winningAmountLabel.setText("You need to select a number!");
            }
            else if (numberAmountField.getText() != "" && (Integer.parseInt(numberField.getText()) < 0 || Integer.parseInt(numberField.getText()) > 36)) {
                winningAmountLabel.setText("The number you selected is not on the board!");
            }
            else if ((redAmount + blackAmount + greenAmount + twelveAmount + numberAmount) > CurrentUser.getCurrentUserBalance()) {
                winningAmountLabel.setText("You dont have enough money to invest!");
            } else {
                winningAmountLabel.setText("");
                start.setDisable(true);
                CurrentUser.changeUserBalance(CurrentUser.getCurrentUserBalance() - redAmount - greenAmount - blackAmount - numberAmount - twelveAmount);
                UserController.updateBalance(CurrentUser.getCurrentUsedId(), CurrentUser.getCurrentUserBalance());
                ball.setVisible(true);
                Thread stopTheAnimation = new Thread(new FewSeconds(pathTransition, results, ball, collect));
                pathTransition.setPath(path);
                pathTransition.setNode(ball);
                pathTransition.setDuration(Duration.millis(2000));
                pathTransition.setCycleCount(Animation.INDEFINITE);
                pathTransition.setAutoReverse(true);
                pathTransition.play();
                stopTheAnimation.start();
                currentBalanceLabel.setText(String.valueOf(CurrentUser.getCurrentUserBalance()));
                redField.setDisable(true);
                blackField.setDisable(true);
                greenFields.setDisable(true);
                twelveFields.setDisable(true);
                numberAmountField.setDisable(true);
                numberField.setDisable(true);
            }
        });


        // Collect button

        collect.setOnAction(actionEvent -> {
            start.setDisable(false);
            collect.setDisable(true);
            int whichTwelveButton = RoulleteUtil.whichTwelveIsSelected(firstTwelveSelect, secondTwelveSelect, thirdTwelveSelect);
            int twelveAmount = RoulleteUtil.checkInput(twelveFields);
            int redAmount = RoulleteUtil.checkInput(redField);
            int blackAmount = RoulleteUtil.checkInput(blackField);
            int greenAmount = RoulleteUtil.checkInput(greenFields);
            int numberAmount = RoulleteUtil.checkInput(numberAmountField);
            int whichNumber = RoulleteUtil.checkInput(numberField);
            int winningNumber = results.get(results.size() - 1);
            int whichTwelve = RoulleteUtil.whichTwelve(winningNumber);
            if (winningNumber == 0) {
                winningNumberLabel.setTextFill(Color.GREEN);
                blackAmount = 0;
                redAmount = 0;
                greenAmount = greenAmount * 36;
            } else if (winningNumber % 2 == 0) {
                winningNumberLabel.setTextFill(Color.RED);
                blackAmount = 0;
                redAmount = redAmount * 2;
                greenAmount = 0;
            } else {
                winningNumberLabel.setTextFill(Color.BLACK);
                redAmount = 0;
                blackAmount = blackAmount * 2;
                greenAmount = 0;
            }
            if (whichTwelveButton == whichTwelve) {
                twelveAmount = twelveAmount * 3;
            } else {
                twelveAmount = 0;
            }
            if (whichNumber == winningNumber) {
                numberAmount = numberAmount * 36;
            } else {
                numberAmount = 0;
            }
            int winningAmount = redAmount + blackAmount + greenAmount + twelveAmount + numberAmount;
            int newAmount = winningAmount + CurrentUser.getCurrentUserBalance();
            winningNumberLabel.setText("Winning number is: " + winningNumber);
            winningAmountLabel.setText("Amount won is: " + winningAmount);
            CurrentUser.changeUserBalance(newAmount);
            UserController.updateBalance(CurrentUser.getCurrentUsedId(), CurrentUser.getCurrentUserBalance());
            currentBalanceLabel.setText(String.valueOf(CurrentUser.getCurrentUserBalance()));
            redField.setDisable(false);
            blackField.setDisable(false);
            greenFields.setDisable(false);
            numberAmountField.setDisable(false);
            numberField.setDisable(false);
            twelveFields.setDisable(false);
        });

        // Change to one method

        blackField.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue,
                                        String newValue) {
                        if (!newValue.matches("\\d*")) {
                            blackField.setText(newValue.replaceAll("[^\\d]", ""));
                        }
                    }
                });

        redField.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue,
                                        String newValue) {
                        if (!newValue.matches("\\d*")) {
                            redField.setText(newValue.replaceAll("[^\\d]", ""));
                        }
                    }
                });

        greenFields.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    greenFields.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        twelveFields.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue,
                                        String newValue) {
                        if (!newValue.matches("\\d*")) {
                            twelveFields.setText(newValue.replaceAll("[^\\d]", ""));
                        }
                    }
                });


    }

    public static void main(String[] args) {
        launch();
    }
}
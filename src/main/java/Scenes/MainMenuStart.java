package Scenes;

import Utils.StyleUtil;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MainMenuStart extends Application {
    private Button roullete = new Button("Roullete");
    private Button blackJack = new Button("Blackjack");
    private Button deposit = new Button("Deposit");
    private Button withdraw = new Button("Withdraw");
    private Button logout = new Button("Logout");
    private Button rating = new Button("Rating");

    @Override
    public void start(Stage stage) throws Exception {

        // Boxes and root

        HBox games = new HBox(roullete, blackJack);
        games.setAlignment(Pos.CENTER);
        VBox root = new VBox(games, deposit, withdraw, rating, logout);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));
        root.setSpacing(20);
        games.setSpacing(20);

        // Style

        root.setStyle("-fx-background-color:#eebddc");
        List<Button> listOfButtons = Arrays.asList(roullete, blackJack, deposit, withdraw, logout, rating);
        for (Button button : listOfButtons) {
            button.setMinWidth(100);
            button.setMinHeight(30);
        }
        StyleUtil.buttonStyle(listOfButtons);

        // Scene

        Scene scene = new Scene(root, 400, 300);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        // Every button

        logout.setOnAction(actionEvent -> {
            stage.close();
            try {
                new LoginMenuStart().start(stage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        roullete.setOnAction(actionEvent -> {
            stage.close();
            try {
                new RouletteStart().start(stage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        withdraw.setOnAction(actionEvent -> {
            stage.close();
            try {
                new WithdrawStart().start(stage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        deposit.setOnAction(actionEvent -> {
            stage.close();
            try {
                new DepositStart().start(stage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        blackJack.setOnAction(actionEvent -> {
            stage.close();
            try {
                new BlackjackStart().start(stage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        rating.setOnAction(actionEvent -> {
            stage.close();
            try {
                new RatingStart().start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}

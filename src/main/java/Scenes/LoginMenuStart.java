package Scenes;

import Classes.CurrentUser;
import Classes.User;
import Database.UserController;
import Exceptions.UserException;
import Utils.StyleUtil;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class LoginMenuStart extends Application {
    private Label userLabel = new Label("Input username:");
    private Label passLabel = new Label("Input password:");
    private Label confirmPassLabel = new Label("Confirm password:");
    private TextField userField = new TextField();
    private PasswordField confirmPassField = new PasswordField();
    private PasswordField passField = new PasswordField();
    private Button loginBtn = new Button("Login");
    private Button registerBtn = new Button("Register");
    private Button registerConfirmButton = new Button("Register");
    private Button backToLoginBtn = new Button("Back to login");
    private Label hiddenLabel = new Label("Incorect username or password!");

    @Override
    public void start(Stage stage) throws IOException {
        // Style

        Font font = new Font("Verdana", 14);
        passField.setFont(font);
        confirmPassField.setFont(font);
        List<Label> labels = Arrays.asList(userLabel, passLabel, confirmPassLabel, hiddenLabel);
        List<Button> buttons = Arrays.asList(loginBtn, registerBtn, registerConfirmButton, backToLoginBtn);
        List<TextField> fields = Arrays.asList(userField);
        StyleUtil.fontLabel(labels);
        StyleUtil.buttonStyle(buttons);
        StyleUtil.fontFields(fields);

        // Boxes and their mesures

        HBox userBox = new HBox(userLabel, userField);
        HBox passBox = new HBox(passLabel, passField);
        HBox confirmBox = new HBox(confirmPassLabel, confirmPassField);
        HBox buttonBox = new HBox(loginBtn, registerBtn);
        VBox root = new VBox(userBox, passBox, buttonBox, hiddenLabel);
        root.setAlignment(Pos.CENTER);

        userLabel.setMinWidth(140);
        passLabel.setMinWidth(140);
        confirmPassLabel.setMinWidth(140);
        hiddenLabel.setVisible(false);
        root.setSpacing(30);
        root.setPadding(new Insets(20));
        buttonBox.setSpacing(30);
        confirmBox.setSpacing(30);
        userBox.setSpacing(30);
        passBox.setSpacing(30);
        root.setStyle("-fx-background-color:#eebddc");
        buttonBox.setAlignment(Pos.CENTER);

        // Scene

        Scene scene = new Scene(root, 400, 300);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        // Login button

        loginBtn.setOnAction(actionEvent -> {
            List<User> listOfUsers = UserController.readUsers();
            for (User x : listOfUsers) {
                if (passField.getText().equals(x.getPassword()) && userField.getText().equalsIgnoreCase(x.getUsername())) {
                    CurrentUser.setCurrentUser(x);
                    stage.close();
                    try {
                        new MainMenuStart().start(stage);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            hiddenLabel.setVisible(true);
            hiddenLabel.setTextFill(Color.RED);
        });

        // Register button

        registerBtn.setOnAction(actionEvent -> {
            buttonBox.getChildren().clear();
            buttonBox.getChildren().addAll(backToLoginBtn, registerConfirmButton);
            root.getChildren().clear();
            root.getChildren().addAll(userBox, passBox, confirmBox, buttonBox, hiddenLabel);
            hiddenLabel.setVisible(false);

        });

        // Register a new user into a database


        registerConfirmButton.setOnAction(actionEvent -> {
            if (passField.getText().equals(confirmPassField.getText())) {
                User newUser = null;
                try {
                    newUser = new User(userField.getText(), passField.getText(), 0);
                } catch (UserException e) {
                    throw new RuntimeException(e);
                }
                UserController.addUser(newUser);
                userField.setText("");
                passField.setText("");
                confirmPassField.setText("");
                hiddenLabel.setText("Register successful");
                hiddenLabel.setTextFill(Color.GREEN);
                hiddenLabel.setVisible(true);
            } else if (!passField.getText().equals(confirmPassField.getText())) {
                hiddenLabel.setText("Passwords not matching");
                hiddenLabel.setTextFill(Color.RED);
                hiddenLabel.setVisible(true);
            } else {
                hiddenLabel.setText("Username already taken");
                hiddenLabel.setTextFill(Color.RED);
                hiddenLabel.setVisible(true);
            }
        });

        // Login on enter

        passField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                loginBtn.fire();
            }
        });

        userField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                loginBtn.fire();
            }
        });

        // Back to login button

        backToLoginBtn.setOnAction(actionEvent -> {
            buttonBox.getChildren().clear();
            buttonBox.getChildren().addAll(loginBtn, registerBtn);
            root.getChildren().clear();
            root.getChildren().addAll(userBox, passBox, buttonBox);
            hiddenLabel.setVisible(false);
        });
    }

    public static void main(String[] args) {
        launch();
    }
}
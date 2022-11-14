module com.example.loginfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.jsoup;


    exports Scenes;
    opens Scenes to javafx.fxml;
    exports Database;
    opens Database to javafx.fxml;
    exports Classes;
    opens Classes to javafx.fxml;
    exports Utils;
    opens Utils to javafx.fxml;
}
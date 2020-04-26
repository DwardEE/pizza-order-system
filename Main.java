package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Creating the GridPane root object
        try {
            GridPane root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            root.setGridLinesVisible(false);
            Scene scene = new Scene(root);
            primaryStage.setTitle("Pizza Order System");
            primaryStage.setScene(scene);
            primaryStage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Launches the program
    public static void main(String[] args) {
        launch(args);
    }
}

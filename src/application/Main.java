package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        AnchorPane root=FXMLLoader.load(getClass().getResource("Main.fxml"));

        Scene scene  = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("�������̽�");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}

package presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        scene = new Scene(loadFXML("startSide"));
        stage = primaryStage;
        stage.setScene(scene);
        stage.setTitle("Credit Management System");
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }



    public static Scene getScene() {
        return scene;
    }

    public static void main(String[] args) {
        launch();
    }

    public static Stage getStage(){
        return stage;
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartSideController.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

}
package presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import presentation.controllers.StartSideController;

import java.io.IOException;
import java.net.URISyntaxException;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage stage;


    public static Scene getScene() {
        return scene;
    }

    public static void main(String[] args) {
        launch();
    }

    public static Stage getStage() {
        return stage;
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        scene = new Scene(loadFXML("startSide"));
        stage = primaryStage;
        try {
            stage.getIcons().add(new Image(String.valueOf(App.class.getResource("pictures/cm.jpg").toURI().toURL())));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        stage.setScene(scene);
        stage.setTitle("Credit Management System");
        stage.show();

    }
}
package it.polimi.ingsw.Client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        //scene = new Scene(loadFXML("home_page"));
        //scene = new Scene(loadFXML("game_board"));
        scene = new Scene(loadFXML("leader_card_selection"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        //System.out.println(App.class.getResource("layouts/game_board.fxml"));
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Scene getScene() {
        return scene;
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("layouts/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void showPopUp(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("layouts/" + fxml + ".fxml"));
        AnchorPane shadowPane = loader.load();

        Scene scene = new Scene(shadowPane);
        Stage stage = new Stage();
        stage.setScene(scene);

        scene.setFill(Color.TRANSPARENT); // Fill our scene with nothing
        stage.initStyle(StageStyle.TRANSPARENT); // Important one!
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
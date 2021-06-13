package it.polimi.ingsw.Client;

import it.polimi.ingsw.Client.GUI.Layout;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {


    private static Scene scene;
    private static ViewBackEnd backEnd;
    private ClientApp app;


    @Override
    public void start(Stage stage) throws IOException {
        app = new ClientApp();
        backEnd = new ViewBackEnd(app);
        //scene = new Scene(loadFXML("home_page"));
       scene = new Scene(loadFXML("game_board"));
        //scene = new Scene(loadFXML("leader_card_selection"));
        stage.setScene(scene);
        stage.setResizable(true);

        setScene("game_board");
        stage.show();
    }

    public static void setScene(String fxml) {
        try{
            scene.setRoot(loadFXML(fxml));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Parent loadFXML(String fxml) throws IOException {

        FXMLLoader loader = new FXMLLoader(App.class.getResource("layouts/" + fxml + ".fxml"));//the ".." might not work in the jar
        Parent root = loader.load();

        try{
            Layout layout = loader.getController();
            layout.setup(backEnd);
        }catch (ClassCastException e){
            e.printStackTrace();
            System.err.println("\n"+fxml+": selected layout doesn't implements Layout interface");
        }

        return root;

    }

    public static void showPopUp(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("layouts/" + fxml + ".fxml"));
        AnchorPane shadowPane = loader.load();

        Scene scene = new Scene(shadowPane);
        Stage stage = new Stage();
        stage.setScene(scene);

        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        //stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
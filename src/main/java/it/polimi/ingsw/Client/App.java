package it.polimi.ingsw.Client;

import it.polimi.ingsw.Client.GUI.FXMLControllers.ErrorPopup;
import it.polimi.ingsw.Client.GUI.FXMLControllers.Game.LeaderCardsTab;
import it.polimi.ingsw.Client.GUI.FXMLControllers.LeaderCardSelection;
import it.polimi.ingsw.Client.GUI.Layout;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.LeaderCard;
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
import java.util.List;

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

        app.setBackEnd(backEnd);

        //scene = new Scene(loadFXML("home_page"));
        scene = new Scene(loadFXML("game_board"));
        //scene = new Scene(loadFXML("leader_card_selection"));
        stage.setScene(scene);
        stage.setResizable(true);

        //setScene("game_board");
        //setScene("home_page");
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

    public static void showErrorPopUp(String errorMessage) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("layouts/error_popup.fxml"));
        AnchorPane shadowPane = loader.load();

        Scene scene = new Scene(shadowPane);
        Stage stage = new Stage();
        stage.setScene(scene);

        ErrorPopup controller = loader.getController();
        controller.setMessage(errorMessage);

        scene.setFill(Color.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public static void showLeaderCardsPopUp(List<LeaderCard> leaderCards, LeaderCardsTab mainController) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("layouts/leader_card_selection.fxml"));
        AnchorPane shadowPane = loader.load();

        Scene scene = new Scene(shadowPane);
        Stage stage = new Stage();
        stage.setScene(scene);

        LeaderCardSelection controller = loader.getController();
        controller.initData(leaderCards, mainController);

        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
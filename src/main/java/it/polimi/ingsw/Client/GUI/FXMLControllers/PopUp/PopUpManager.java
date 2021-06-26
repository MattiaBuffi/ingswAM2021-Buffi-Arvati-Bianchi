package it.polimi.ingsw.Client.GUI.FXMLControllers.PopUp;

import it.polimi.ingsw.Client.App;
import it.polimi.ingsw.Client.GUI.FXMLControllers.Game.LeaderCardsTab;
import it.polimi.ingsw.Client.GUI.FXMLControllers.Game.StorageTab;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.LeaderCard;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Model.Marble.Marble;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.List;

public class PopUpManager {

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

    public static void showDepositResourcePopUp(Marble marble, StorageTab mainController, boolean discardVisibility) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("layouts/resource_available.fxml"));
        AnchorPane shadowPane = loader.load();

        Scene scene = new Scene(shadowPane);
        Stage stage = new Stage();
        stage.setScene(scene);

        ResourceAvailablePopup controller = loader.getController();
        controller.initData(marble, mainController, discardVisibility);

        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public static void showMoveResourcePopUp(int selection, StorageTab mainController) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("layouts/move_resource.fxml"));
        AnchorPane shadowPane = loader.load();

        Scene scene = new Scene(shadowPane);
        Stage stage = new Stage();
        stage.setScene(scene);

        MoveResourcePopup controller = loader.getController();
        controller.initData(mainController, selection);

        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public static void showStartingResourcesPopUp(int resourceNumber, StorageTab mainController) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("layouts/starting_resources.fxml"));
        AnchorPane shadowPane = loader.load();

        Scene scene = new Scene(shadowPane);
        Stage stage = new Stage();
        stage.setScene(scene);

        StartingResourcesPopUp controller = loader.getController();
        controller.initData(mainController, resourceNumber);

        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public static void showPlayerBoardPopUp(String username, ViewBackEnd backend) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("layouts/player_board.fxml"));
        AnchorPane shadowPane = loader.load();

        Scene scene = new Scene(shadowPane);
        Stage stage = new Stage();
        stage.setScene(scene);

        PlayerBoard controller = loader.getController();
        controller.initData(username, backend);

        scene.setFill(Color.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

}

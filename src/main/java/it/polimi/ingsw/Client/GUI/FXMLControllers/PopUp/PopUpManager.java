package it.polimi.ingsw.Client.GUI.FXMLControllers.PopUp;

import it.polimi.ingsw.Client.App;
import it.polimi.ingsw.Client.GUI.FXMLControllers.Game.LeaderCardsTab;
import it.polimi.ingsw.Client.GUI.FXMLControllers.Game.StorageTab;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.LeaderCard;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Model.Marble.Marble;
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
 * Class to manage all the pop-up windows that can be displayed by the GUI. Every method of the class is static and
 * load a different fxml file.
 */
public class PopUpManager {

    public static void showErrorPopUp(String errorMessage) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("layouts/error_popup.fxml"));
        Parent parent = loader.load();

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);

        ErrorPopup controller = loader.getController();
        controller.setMessage(errorMessage);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public static void showNewTurnPopUp(String username, ViewBackEnd backEnd) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("layouts/new_turn.fxml"));
        Parent parent = loader.load();

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);

        NewTurnPopUp controller = loader.getController();
        controller.initData(username, backEnd);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public static void showActionTokenPopUp(String message) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("layouts/action_token.fxml"));
        Parent parent = loader.load();

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);

        ActionTokenPopUp controller = loader.getController();
        controller.initData(message);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public static void showLeaderCardsPopUp(List<LeaderCard> leaderCards, LeaderCardsTab mainController) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("layouts/leader_card_selection.fxml"));
        Parent parent = loader.load();

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);

        LeaderCardSelection controller = loader.getController();
        controller.initData(leaderCards, mainController);

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public static void showDepositResourcePopUp(Marble marble, StorageTab mainController, boolean discardVisibility) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("layouts/resource_available.fxml"));
        Parent parent = loader.load();

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);

        ResourceAvailablePopup controller = loader.getController();
        controller.initData(marble, mainController, discardVisibility);

        stage.initStyle(StageStyle.TRANSPARENT);
        //stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public static void showMoveResourcePopUp(int selection, StorageTab mainController) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("layouts/move_resource.fxml"));
        Parent parent = loader.load();

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);

        MoveResourcePopup controller = loader.getController();
        controller.initData(mainController, selection);

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public static void showStartingResourcesPopUp(int resourceNumber, StorageTab mainController) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("layouts/starting_resources.fxml"));
        Parent parent = loader.load();

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);

        StartingResourcesPopUp controller = loader.getController();
        controller.initData(mainController, resourceNumber);

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public static void showPlayerBoardPopUp(String username, ViewBackEnd backend) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("layouts/player_board.fxml"));
        Parent parent = loader.load();

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);

        PlayerBoard controller = loader.getController();
        controller.initData(username, backend);

        scene.setFill(Color.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

}

/*
AnchorPane shadowPane = loader.load();

Scene scene = new Scene(shadowPane);

scene.setFill(Color.TRANSPARENT);
*/

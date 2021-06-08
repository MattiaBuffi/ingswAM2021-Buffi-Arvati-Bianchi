package it.polimi.ingsw.Client.GUI.FXMLControllers;

import it.polimi.ingsw.Client.GUI.ControllerManager;

import it.polimi.ingsw.Controller.ClientMessageController;
import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Utils.Observable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;


public class StorageTab extends Observable<Message<ClientEventHandler>> implements Initializable {
    @FXML
    ImageView res1_1, res2_1, res2_2, res3_1, res3_2, res3_3, resLeader1, resLeader2;
    @FXML
    Label yellowValue, purpleValue, blueValue, greyValue;
    @FXML
    Pane leaderStorageContainer;

    private void changeResourceValue(String color, int value){
        switch (color){
            case "YELLOW":
                yellowValue.setText(String.valueOf(value));
                break;
            case "PURPLE":
                purpleValue.setText(String.valueOf(value));
                break;
            case "BLUE":
                blueValue.setText(String.valueOf(value));
                break;
            case "GREY":
                greyValue.setText(String.valueOf(value));
                break;
        }
    }

    private void resetValue(){
        yellowValue.setText("0");
        purpleValue.setText("0");
        blueValue.setText("0");
        greyValue.setText("0");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        leaderStorageContainer.setVisible(false);
        resetValue();
        ControllerManager.addController(this);
        addObserver(new ClientMessageController());
    }
}

package it.polimi.ingsw.Client.GUI.FXMLControllers;

import it.polimi.ingsw.Client.App;
import it.polimi.ingsw.Controller.ClientMessageController;
import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.ClientMessages.GameSize;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Utils.Observable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NumberOfPlayerPage extends Observable<Message<ClientEventHandler>> implements Initializable {
    @FXML
    ChoiceBox<Integer> choiceBox;

    public void goToWaitingRoom(){
        //System.out.println("ITEM - " + choiceBox.getSelectionModel().getSelectedItem());
        GameSize message = new GameSize(choiceBox.getSelectionModel().getSelectedItem());
        notify(message);
        try {
            showPopUp();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showPopUp() throws IOException {
        App.showPopUp("waiting_page");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addObserver(new ClientMessageController());
    }
}

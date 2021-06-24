package it.polimi.ingsw.Client.GUI.FXMLControllers;

import it.polimi.ingsw.Client.App;
import it.polimi.ingsw.Client.GUI.Layout;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientMessages.GameSize;
import it.polimi.ingsw.Message.Model.WaitingPlayersUpdate;
import it.polimi.ingsw.Message.ModelEventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;

public class NumberOfPlayerPage extends ModelEventHandler.Default implements Layout{
    @FXML
    ChoiceBox<Integer> choiceBox;


    private ViewBackEnd backEnd;

    @Override
    public void setup(ViewBackEnd backEnd) {
        this.backEnd = backEnd;
        this.backEnd.setEventHandler(this);
    }


    public void goToWaitingRoom(){
        //System.out.println("ITEM - " + choiceBox.getSelectionModel().getSelectedItem());
        GameSize message = new GameSize(choiceBox.getSelectionModel().getSelectedItem());
        backEnd.notify(message);
        App.setScene("waiting_page");
        /*try {
            showPopUp();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public void invalidMessage() {

    }

}

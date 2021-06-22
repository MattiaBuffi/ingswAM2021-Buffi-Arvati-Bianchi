package it.polimi.ingsw.Client.GUI.FXMLControllers;

import it.polimi.ingsw.Client.App;
import it.polimi.ingsw.Client.GUI.Layout;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientMessages.GameSize;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;

public class NumberOfPlayerPage implements Layout{
    @FXML
    ChoiceBox<Integer> choiceBox;

    private final String NEXT_SCENE = "waiting_page";
    private ViewBackEnd backEnd;

    @Override
    public void setup(ViewBackEnd backEnd) {
        this.backEnd = backEnd;
    }


    public void goToWaitingRoom(){
        //System.out.println("ITEM - " + choiceBox.getSelectionModel().getSelectedItem());
        if(choiceBox.getSelectionModel().getSelectedItem()!=null) {
            GameSize message = new GameSize(choiceBox.getSelectionModel().getSelectedItem());
            backEnd.notify(message);

            App.setScene(NEXT_SCENE);
        }
    }

}

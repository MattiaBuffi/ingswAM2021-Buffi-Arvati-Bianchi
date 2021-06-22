package it.polimi.ingsw.Client.GUI.FXMLControllers;

import it.polimi.ingsw.Client.App;
import it.polimi.ingsw.Client.GUI.Layout;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.Model.*;
import it.polimi.ingsw.Message.ModelEventHandler;
import javafx.scene.control.Label;


public class WaitingPage extends ModelEventHandler.Default implements Layout{

    public Label playerNumber;
    private ViewBackEnd backEnd;


    @Override
    public void setup(ViewBackEnd backEnd) {
        this.backEnd = backEnd;
        backEnd.setEventHandler(this);
    }


    @Override
    public void invalidMessage() {

    }

    @Override
    public void handle(WaitingPlayersUpdate event) {
        playerNumber.setText(event.getLobbyCurrentSize() +" players connected");
    }

    @Override
    public void handle(GameSizeRequest event) {
        App.setScene("number_of_player_page");
    }

}


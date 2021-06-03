package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.SingleClientEventHandler;
import it.polimi.ingsw.Message.ClientMessage.ActivateLeaderCard;
import it.polimi.ingsw.Message.ClientMessage.ThrowCard;
import it.polimi.ingsw.Utils.Observer;

public class GameController implements Observer,SingleClientEventHandler {


    @Override
    public void handle(ActivateLeaderCard event) {

    }

    @Override
    public void handle(ThrowCard event) {

    }

    @Override
    public void update(Object event) {

    }



}

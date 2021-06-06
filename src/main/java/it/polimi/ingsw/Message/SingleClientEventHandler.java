package it.polimi.ingsw.Message;

import it.polimi.ingsw.Message.ClientMessage.ActivateLeaderCard;
import it.polimi.ingsw.Message.ClientMessage.ThrowCard;



public interface SingleClientEventHandler {

    void handle(ActivateLeaderCard event);

    void handle(ThrowCard event);


}

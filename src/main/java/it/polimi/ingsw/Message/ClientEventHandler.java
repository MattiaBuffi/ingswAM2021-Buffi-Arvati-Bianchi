package it.polimi.ingsw.Message;

import it.polimi.ingsw.Message.ClientMessage.ActivateLeaderCard;
import it.polimi.ingsw.Message.ClientMessage.GameSize;
import it.polimi.ingsw.Message.ClientMessage.Login;
import it.polimi.ingsw.Message.ClientMessage.ThrowCard;



public interface ClientEventHandler {

    void handle(ActivateLeaderCard event);

    void handle(ThrowCard event);


    void handle(Login event);

    void handle(GameSize event);

}

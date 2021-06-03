package it.polimi.ingsw.Message;

import it.polimi.ingsw.Message.ClientMessage.GameSize;
import it.polimi.ingsw.Message.ClientMessage.Login;

public interface MultipleClientEventHandler extends SingleClientEventHandler {

    void handle(Login event);

    void handle(GameSize event);

}

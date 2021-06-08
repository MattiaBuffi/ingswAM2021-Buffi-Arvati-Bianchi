package it.polimi.ingsw.Message;

import it.polimi.ingsw.Message.ClientMessages.GameSize;
import it.polimi.ingsw.Message.ClientMessages.Login;

public interface MultipleClientEventHandler extends SingleClientEventHandler {

    void handle(Login event);

    void handle(GameSize event);



}

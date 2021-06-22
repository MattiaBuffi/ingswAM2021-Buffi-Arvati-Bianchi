package it.polimi.ingsw.Message.ClientMessages;

import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.Message;

public class Ping implements Message<ClientEventHandler> {


    @Override
    public void accept(ClientEventHandler handler) {
        handler.handle(this);
    }
}

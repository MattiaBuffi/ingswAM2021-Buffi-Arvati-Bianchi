package it.polimi.ingsw.Message.ClientMessages;

import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.Message;

public class Ping implements Message<ClientEventHandler> {


    private static final long serialVersionUID = 7803309048841778764L;

    @Override
    public void accept(ClientEventHandler handler) {
        handler.handle(this);
    }
}

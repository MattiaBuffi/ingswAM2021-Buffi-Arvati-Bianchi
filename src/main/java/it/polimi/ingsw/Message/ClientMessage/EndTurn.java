package it.polimi.ingsw.Message.ClientMessage;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.SingleClientEventHandler;

public class EndTurn implements Message<SingleClientEventHandler> {

    @Override
    public void accept(SingleClientEventHandler handler) {
        handler.handle(this);
    }
}

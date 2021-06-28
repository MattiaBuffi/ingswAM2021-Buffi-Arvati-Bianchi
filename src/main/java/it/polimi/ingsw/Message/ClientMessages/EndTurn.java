package it.polimi.ingsw.Message.ClientMessages;

import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.Message;

/**
 * The message report the end of the turn of the current player.
 */
public class EndTurn implements Message<ClientEventHandler> {

    @Override
    public void accept(ClientEventHandler handler) {
        handler.handle(this);
    }
}

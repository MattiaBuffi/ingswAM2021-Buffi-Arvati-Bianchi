package it.polimi.ingsw.Message.ClientMessages;

import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.Message;

/**
 * Message to report the end of the turn of the current player.
 */
public class EndTurn implements Message<ClientEventHandler> {

    private static final long serialVersionUID = -5788925024950881076L;

    @Override
    public void accept(ClientEventHandler handler) {
        handler.handle(this);
    }
}

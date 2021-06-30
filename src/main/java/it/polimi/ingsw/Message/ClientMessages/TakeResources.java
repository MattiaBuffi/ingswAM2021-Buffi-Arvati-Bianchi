package it.polimi.ingsw.Message.ClientMessages;

import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.Message;

/**
 * Message to report the taking of resources from the market. The field is a integer between 0 and 6:
 * - 0 to 3 for the columns of the market
 * - 4 to 6 for the rows of the market
 */
public class TakeResources implements Message<ClientEventHandler> {

    private final int selection;

    public TakeResources(int selection) {
        this.selection = selection;
    }

    public int getSelection() {
        return selection;
    }

    @Override
    public void accept(ClientEventHandler handler) {
        handler.handle(this);
    }

}

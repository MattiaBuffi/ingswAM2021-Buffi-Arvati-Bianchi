package it.polimi.ingsw.Message.ClientMessages;

import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.SingleClientEventHandler;

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

package it.polimi.ingsw.Message.ClientMessage;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.SingleClientEventHandler;

public class TakeResources implements Message<SingleClientEventHandler> {
    private final int selection;

    public TakeResources(int selection) {
        this.selection = selection;
    }

    public int getSelection() {
        return selection;
    }

    @Override
    public void accept(SingleClientEventHandler handler) {
        handler.handle(this);
    }
}
package it.polimi.ingsw.Message.Model;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;

public class VaticanReport implements Message<ModelEventHandler> {

    private final int index;

    public VaticanReport(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public void accept(ModelEventHandler handler) {
        handler.handle(this);
    }
}

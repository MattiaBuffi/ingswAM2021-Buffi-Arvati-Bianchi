package it.polimi.ingsw.Message.Model;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;

public class EndGame implements Message<ModelEventHandler> {
    private static final long serialVersionUID = -5820489547003909634L;

    @Override
    public void accept(ModelEventHandler handler) {
        handler.handle(this);
    }
}

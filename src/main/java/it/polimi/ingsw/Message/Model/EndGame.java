package it.polimi.ingsw.Message.Model;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;

public class EndGame implements Message<ModelEventHandler> {
    @Override
    public void accept(ModelEventHandler handler) {
        handler.handle(this);
    }
}

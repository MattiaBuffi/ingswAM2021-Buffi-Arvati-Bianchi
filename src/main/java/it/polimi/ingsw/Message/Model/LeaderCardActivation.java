package it.polimi.ingsw.Message.Model;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;

public class LeaderCardActivation implements Message<ModelEventHandler> {

    private String id;

    @Override
    public void accept(ModelEventHandler handler) {
        handler.handle(this);
    }

    public String getId() {
        return id;
    }
}

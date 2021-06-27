package it.polimi.ingsw.Message.Model;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;

public class ActionTokenPlayed implements Message<ModelEventHandler> {

    private String message;

    public ActionTokenPlayed(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void accept(ModelEventHandler handler) {
        handler.handle(this);
    }

}

package it.polimi.ingsw.Message.Model;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;

public class ActivePlayer implements Message<ModelEventHandler> {

    private static final long serialVersionUID = -1601615031445751828L;

    private String username;

    public ActivePlayer(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public void accept(ModelEventHandler handler) {
        handler.handle(this);
    }

}
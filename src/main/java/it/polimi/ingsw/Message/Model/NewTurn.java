package it.polimi.ingsw.Message.Model;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;

public class NewTurn implements Message<ModelEventHandler> {

    private final String username;

    public NewTurn(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public void accept(ModelEventHandler handler) {

    }
}

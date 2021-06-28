package it.polimi.ingsw.Message.Model;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;

import java.util.List;


public class ModelUpdate implements Message<ModelEventHandler> {


    private String playerUsername;
    private List<Message<ModelEventHandler>> messages;

    public ModelUpdate(String playerUsername, List<Message<ModelEventHandler>> messages) {
        this.playerUsername = playerUsername;
        this.messages = messages;
    }

    public String getPlayerUsername() {
        return playerUsername;
    }

    public List<Message<ModelEventHandler>> getMessages() {
        return messages;
    }

    @Override
    public void accept(ModelEventHandler handler) {
        handler.handle(this);
    }


}
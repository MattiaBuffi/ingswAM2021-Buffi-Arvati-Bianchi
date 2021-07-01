package it.polimi.ingsw.Message.Model;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;

import java.util.List;


public class ModelUpdate implements Message<ModelEventHandler> {


    private static final long serialVersionUID = -5332943090767541181L;
    private String playerUsername;
    private String message;
    private List<Message<ModelEventHandler>> messages;

    public ModelUpdate(String playerUsername, String message, List<Message<ModelEventHandler>> messages) {
        this.playerUsername = playerUsername;
        this.message = message;
        this.messages = messages;
    }

    public String getPlayerUsername() {
        return playerUsername;
    }

    public String getMessage() {
        return message;
    }

    public List<Message<ModelEventHandler>> getMessages() {
        return messages;
    }

    @Override
    public void accept(ModelEventHandler handler) {
        handler.handle(this);
    }


}
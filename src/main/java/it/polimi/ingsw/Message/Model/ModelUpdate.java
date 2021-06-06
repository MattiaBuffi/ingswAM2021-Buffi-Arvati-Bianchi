package it.polimi.ingsw.Message.Model;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;

import java.util.List;


public class ModelUpdate implements Message<ModelEventHandler> {

    private List<Message<ModelEventHandler>> messages;


    public ModelUpdate(List<Message<ModelEventHandler>> messages) {
        this.messages = messages;
    }


    public List<Message<ModelEventHandler>> getMessages() {
        return messages;
    }


    @Override
    public void accept(ModelEventHandler handler) {
        handler.handle(this);
    }


}
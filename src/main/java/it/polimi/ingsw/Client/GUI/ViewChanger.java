package it.polimi.ingsw.Client.GUI;

import it.polimi.ingsw.Client.View.View;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.Model.ErrorUpdate;
import it.polimi.ingsw.Message.Model.ModelUpdate;
import it.polimi.ingsw.Message.ModelEventHandler;

import java.util.List;

public class ViewChanger implements View {
    private List<Message<ModelEventHandler>> updates;
    private final ViewChangesHandler handler = new ViewChangesHandler();

    @Override
    public void updateView(ModelUpdate message) {
        updates = message.getMessages();
        for(Message<ModelEventHandler> m : updates){
            m.accept(handler);
        }
    }

    @Override
    public void displayError(ErrorUpdate message) {
        /**
         * TODO: Display with pop up window the error
         */
    }


}

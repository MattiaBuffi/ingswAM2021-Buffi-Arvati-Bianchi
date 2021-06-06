package it.polimi.ingsw.Client;

import it.polimi.ingsw.Client.ModelData.ViewModel;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;
import it.polimi.ingsw.Utils.Observer;

public class MessageHandler implements ModelEventHandler, Observer<Message> {

    private ViewModel model;
    private View view;
    
    @Override
    public void update(Message event){
        event.accept(this);
    }

}

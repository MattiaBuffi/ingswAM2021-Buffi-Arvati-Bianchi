package it.polimi.ingsw.Model;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;

public interface EventBroadcaster {

    void notifyAllPlayers(Message<ModelEventHandler> event);

    void notifyUser(Message<ModelEventHandler> event);

}

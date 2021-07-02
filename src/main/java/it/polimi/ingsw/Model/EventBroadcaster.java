package it.polimi.ingsw.Model;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;

public interface EventBroadcaster {

    /**
     * Notify to all the player of the game the message
     * @param event message to notify
     */
    void notifyAllPlayers(Message<ModelEventHandler> event);

    /**
     * Notify to one player the message
     * @param event message to notify
     */
    void notifyUser(Message<ModelEventHandler> event);

}

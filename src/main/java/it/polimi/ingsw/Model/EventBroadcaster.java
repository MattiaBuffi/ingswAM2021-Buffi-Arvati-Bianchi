package it.polimi.ingsw.Model;

import it.polimi.ingsw.Message.Message;

public interface EventBroadcaster {

    void notifyAllPlayers(Message event);

    void notifyUser(Message event);

}

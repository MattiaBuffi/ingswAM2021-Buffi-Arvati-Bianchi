package it.polimi.ingsw.Client;

import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;
import it.polimi.ingsw.Utils.Observable;
import it.polimi.ingsw.Utils.Observer;

public abstract class Controller extends Observable<Message<ModelEventHandler>> implements Observer<Message<ClientEventHandler>> {

    public abstract void disconnect();

}

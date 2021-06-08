package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Utils.Observer;

public class ClientMessageController implements Observer<Message<ClientEventHandler>> {
    private final ClientMessageHandler handler = new ClientMessageHandler();

    @Override
    public void update(Message<ClientEventHandler> event) {
        event.accept(handler);
    }
}

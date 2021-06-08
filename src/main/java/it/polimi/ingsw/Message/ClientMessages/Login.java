package it.polimi.ingsw.Message.ClientMessages;

import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.MultipleClientEventHandler;
import it.polimi.ingsw.Message.SingleClientEventHandler;

public class Login implements Message<ClientEventHandler> {

    private String username;

    public Login(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public void accept(ClientEventHandler handler) {
        handler.handle(this);
    }
}

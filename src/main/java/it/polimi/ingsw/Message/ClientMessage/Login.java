package it.polimi.ingsw.Message.ClientMessage;

import it.polimi.ingsw.Message.MultipleClientEventHandler;
import it.polimi.ingsw.Message.Message;

public class Login implements Message<MultipleClientEventHandler> {

    private String username;

    public Login(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public void accept(MultipleClientEventHandler handler) {
        handler.handle(this);
    }

}

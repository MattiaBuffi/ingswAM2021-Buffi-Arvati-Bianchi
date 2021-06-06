package it.polimi.ingsw.Message.ClientMessage;

import it.polimi.ingsw.Message.MultipleClientEventHandler;
import it.polimi.ingsw.Message.Message;

public class Login implements Message<MultipleClientEventHandler> {

    private String username;
    private String id;

    public Login(String username, String id) {
        this.username = username;
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public String getId() {
        return id;
    }


    @Override
    public void accept(MultipleClientEventHandler handler) {
        handler.handle(this);
    }



}

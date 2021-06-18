package it.polimi.ingsw.Message.ClientMessage;

import it.polimi.ingsw.Message.ServerEventHandler;
import it.polimi.ingsw.Message.Message;

public class Login implements Message<ServerEventHandler> {

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
    public void accept(ServerEventHandler handler) {
        handler.handle(this);
    }



}

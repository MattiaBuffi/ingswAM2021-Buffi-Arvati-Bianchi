package it.polimi.ingsw.Message.ClientMessages;

import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.Message;

/**
 * Message for the username of the player. Sent during the setup of the game
 */
public class Login implements Message<ClientEventHandler> {

    private static final long serialVersionUID = -6877611883108033489L;
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

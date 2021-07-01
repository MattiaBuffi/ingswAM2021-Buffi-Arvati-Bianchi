package it.polimi.ingsw.Message.ClientMessages;

import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.Message;

/**
 * Message sent by the player who create a game and indicate the number of the players of the game just created. Sent
 * during the setup of the game
 */
public class GameSize implements Message<ClientEventHandler> {

    private static final long serialVersionUID = -5612256373634416967L;
    private final int size;

    public GameSize(int size){
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void accept(ClientEventHandler handler) {
        handler.handle(this);
    }


}

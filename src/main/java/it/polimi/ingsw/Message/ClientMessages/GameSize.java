package it.polimi.ingsw.Message.ClientMessages;

import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.Message;

/**
 * The message is sent by the player who create a game and indicate the number of the players of the game just created.
 */
public class GameSize implements Message<ClientEventHandler> {

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

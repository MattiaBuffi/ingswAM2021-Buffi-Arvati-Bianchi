package it.polimi.ingsw.Message.ClientMessages;

import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.MultipleClientEventHandler;
import it.polimi.ingsw.Message.SingleClientEventHandler;

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

package it.polimi.ingsw.Message.ClientMessage;

import it.polimi.ingsw.Message.ServerEventHandler;
import it.polimi.ingsw.Message.Message;

public class GameSize implements Message<ServerEventHandler> {

    private final int size;

    public GameSize(int size){
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void accept(ServerEventHandler handler) {
        handler.handle(this);
    }
}

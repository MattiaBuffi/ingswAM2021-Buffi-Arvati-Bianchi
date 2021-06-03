package it.polimi.ingsw.Message.ClientMessage;

import it.polimi.ingsw.Message.MultipleClientEventHandler;
import it.polimi.ingsw.Message.Message;

public class GameSize implements Message<MultipleClientEventHandler> {

    private final int size;

    public GameSize(int size){
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void accept(MultipleClientEventHandler handler) {
        handler.handle(this);
    }
}

package it.polimi.ingsw.Message.ClientMessages;

import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.Message;

/**
 * Message for the movement of resources from one shelf to another. The fields are the the initial shelf and the final one.
 * Shelf index are from 0 to 2 for normal shelves and, eventually, 3 and 4 for additionally leader storage.
 */
public class MoveResources implements Message<ClientEventHandler> {
    private int startPos;
    private int endPos;

    public MoveResources(int startPos, int endPos) {
        this.startPos = startPos;
        this.endPos = endPos;
    }

    public int getStartPos() {
        return startPos;
    }

    public int getEndPos() {
        return endPos;
    }

    @Override
    public void accept(ClientEventHandler handler) {
        handler.handle(this);
    }
}

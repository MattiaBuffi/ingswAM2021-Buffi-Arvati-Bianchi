package it.polimi.ingsw.Message.ClientMessages;

import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.Message;

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

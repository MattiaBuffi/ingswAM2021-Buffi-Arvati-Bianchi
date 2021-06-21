package it.polimi.ingsw.Message.Model;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;

public class GameSizeRequest implements Message<ModelEventHandler> {

    private int lobbyCurrentSize;

    public GameSizeRequest(int lobbyCurrentSize){
        this.lobbyCurrentSize = lobbyCurrentSize;
    }

    public int getLobbyCurrentSize() {
        return lobbyCurrentSize;
    }

    @Override
    public void accept(ModelEventHandler handler) {
        handler.handle(this);
    }
}

package it.polimi.ingsw.Message.Model;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;

public class WaitingPlayersUpdate implements Message<ModelEventHandler> {

    private static final long serialVersionUID = 8123868180134820075L;
    private int lobbyCurrentSize;

    public WaitingPlayersUpdate(int lobbyCurrentSize){
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
package it.polimi.ingsw.Message.Model;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;

import java.util.List;

public class VaticanReport implements Message<ModelEventHandler> {

    private final int index;
    private final List<String> players;

    public VaticanReport(int index, List<String> players) {
        this.index = index;
        this.players = players;
    }

    public int getIndex() {
        return index;
    }

    public List<String> getPlayers() {
        return players;
    }

    @Override
    public void accept(ModelEventHandler handler) {
        handler.handle(this);
    }
}

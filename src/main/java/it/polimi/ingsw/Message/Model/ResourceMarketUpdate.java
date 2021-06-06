package it.polimi.ingsw.Message.Model;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;
import it.polimi.ingsw.Model.Marble.Marble;

import java.util.List;

public class ResourceMarketUpdate implements Message<ModelEventHandler> {

    private int position;
    private List<Marble> marbles;

    public ResourceMarketUpdate(int position, List<Marble> marbles) {
        this.position = position;
        this.marbles = marbles;
    }

    public int getPosition() {
        return position;
    }

    public List<Marble> getMarbles() {
        return marbles;
    }

    @Override
    public void accept(ModelEventHandler handler) {
        handler.handle(this);
    }

}

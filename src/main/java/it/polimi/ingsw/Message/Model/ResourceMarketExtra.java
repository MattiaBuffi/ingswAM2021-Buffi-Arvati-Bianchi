package it.polimi.ingsw.Message.Model;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;
import it.polimi.ingsw.Model.Marble.Marble;


public class ResourceMarketExtra implements Message<ModelEventHandler> {

    private static final long serialVersionUID = 202942607602938582L;
    private final Marble marble;

    public ResourceMarketExtra(Marble marble) {
        this.marble = marble;
    }

    public Marble getMarble() {
        return marble;
    }

    @Override
    public void accept(ModelEventHandler handler) {
        handler.handle(this);
    }


}
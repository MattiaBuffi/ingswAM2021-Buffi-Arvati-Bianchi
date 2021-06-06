package it.polimi.ingsw.Message.Model;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;
import it.polimi.ingsw.Model.Marble.Marble;

public class MarketResourceAvailable implements Message<ModelEventHandler> {

    private Marble marble;

    public MarketResourceAvailable(Marble marble){
        this.marble = marble;
    }


    public Marble getColor() {
        return marble;
    }


    @Override
    public void accept(ModelEventHandler handler) {
        handler.handle(this);
    }


}

package it.polimi.ingsw.Message.Model;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;
import it.polimi.ingsw.Model.Marble.Marble;

public class MarketResourceTaken implements Message<ModelEventHandler> {

    private static final long serialVersionUID = 930195985542110866L;
    private Marble.Color marble;

    public MarketResourceTaken(Marble.Color marble){
        this.marble = marble;
    }


    public Marble.Color getColor() {
        return marble;
    }


    @Override
    public void accept(ModelEventHandler handler) {
        handler.handle(this);
    }

}
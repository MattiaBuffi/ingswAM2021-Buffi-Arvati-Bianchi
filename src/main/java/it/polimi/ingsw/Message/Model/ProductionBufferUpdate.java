package it.polimi.ingsw.Message.Model;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;
import it.polimi.ingsw.Model.Marble.Marble;


import java.util.List;

public class ProductionBufferUpdate implements Message<ModelEventHandler> {

    private String id;
    private List<Marble> produced;

    public ProductionBufferUpdate(String id, List<Marble> produced) {
        this.id = id;
        this.produced = produced;
    }


    public String getId() {
        return id;
    }

    public List<Marble> getProduced() {
        return produced;
    }


    @Override
    public void accept(ModelEventHandler handler) {
        handler.handle(this);
    }


}
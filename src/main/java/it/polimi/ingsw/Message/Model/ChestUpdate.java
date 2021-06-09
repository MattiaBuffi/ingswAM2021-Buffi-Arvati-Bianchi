package it.polimi.ingsw.Message.Model;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;
import it.polimi.ingsw.Model.Marble.ResourceList;

public class ChestUpdate implements Message<ModelEventHandler> {

    private final ResourceList resources;

    public ChestUpdate(ResourceList resources){
        this.resources = resources;
    }


    public ResourceList getResources() {
        return resources;
    }


    @Override
    public void accept(ModelEventHandler handler) {
        handler.handle(this);
    }


}
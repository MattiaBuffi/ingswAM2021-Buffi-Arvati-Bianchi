package it.polimi.ingsw.Message.Model;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;

public class ResourceSetup implements Message<ModelEventHandler> {

    private int availableResources;

    public ResourceSetup(int availableResources) {
        this.availableResources = availableResources;
    }

    public int getAvailableResources() {
        return availableResources;
    }

    @Override
    public void accept(ModelEventHandler handler) {
        handler.handle(this);
    }
}

package it.polimi.ingsw.Message.Model;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;

import java.util.List;


public class DevelopmentCardBuyUpdate implements Message<ModelEventHandler> {

    private final int position;

    private final String id;
    private final int victoryPoints;
    private final ResourceList require;
    private final ResourceList produce;

    public DevelopmentCardBuyUpdate(int position, String id, int victoryPoints, ResourceList require, ResourceList produce) {
        this.position = position;
        this.id = id;
        this.victoryPoints = victoryPoints;
        this.require = require;
        this.produce = produce;
    }


    public int getPosition() {
        return position;
    }

    public String getId() {
        return id;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public ResourceList getRequire() {
        return require;
    }

    public ResourceList getProduce() {
        return produce;
    }

    @Override
    public void accept(ModelEventHandler handler) {
        handler.handle(this);
    }


}

package it.polimi.ingsw.Message.Model;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;
import it.polimi.ingsw.Model.ProductionCard.DevelopmentCard;

import java.util.List;


public class DevelopmentCardBuyUpdate implements Message<ModelEventHandler> {

    private static final long serialVersionUID = -641990858933602750L;
    private final int position;

    private final DevelopmentCard.Color color;
    private final String id;
    private final int victoryPoints;
    private final ResourceList require;
    private final ResourceList produce;

    public DevelopmentCardBuyUpdate(int position, String id, int victoryPoints, ResourceList require, ResourceList produce, DevelopmentCard.Color color) {
        this.position = position;
        this.id = id;
        this.victoryPoints = victoryPoints;
        this.require = require;
        this.produce = produce;
        this.color = color;
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

    public DevelopmentCard.Color getColor(){return color;}
    @Override
    public void accept(ModelEventHandler handler) {
        handler.handle(this);
    }


}

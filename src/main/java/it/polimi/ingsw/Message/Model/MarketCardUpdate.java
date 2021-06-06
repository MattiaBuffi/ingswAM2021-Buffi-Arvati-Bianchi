package it.polimi.ingsw.Message.Model;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;

import java.util.List;


public class MarketCardUpdate implements Message<ModelEventHandler> {

    private int x;
    private int y;

    private String id;
    private int victoryPoints;
    private ResourceList price;
    private ResourceList require;
    private ResourceList produce;


    public MarketCardUpdate(int x, int y, String id, int victoryPoints, ResourceList price, ResourceList require, ResourceList produce) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.victoryPoints = victoryPoints;
        this.price = price;
        this.require = require;
        this.produce = produce;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getId() {
        return id;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public ResourceList getPrice() {
        return price;
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
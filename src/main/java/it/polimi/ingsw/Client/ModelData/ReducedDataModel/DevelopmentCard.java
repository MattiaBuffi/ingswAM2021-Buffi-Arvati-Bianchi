package it.polimi.ingsw.Client.ModelData.ReducedDataModel;

import it.polimi.ingsw.Model.Marble.MarbleColor;

import java.util.List;

public class DevelopmentCard {

    private String id;
    private int victoryPoints;
    private List<MarbleColor> price;
    private List<MarbleColor> require;
    private List<MarbleColor> produce;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public List<MarbleColor> getPrice() {
        return price;
    }

    public void setPrice(List<MarbleColor> price) {
        this.price = price;
    }

    public List<MarbleColor> getRequire() {
        return require;
    }

    public void setRequire(List<MarbleColor> require) {
        this.require = require;
    }

    public List<MarbleColor> getProduce() {
        return produce;
    }

    public void setProduce(List<MarbleColor> produce) {
        this.produce = produce;
    }
}

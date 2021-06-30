package it.polimi.ingsw.Client.ModelData.ReducedDataModel;

import it.polimi.ingsw.Model.Marble.ResourceList;
import it.polimi.ingsw.Model.ProductionCard.DevelopmentCard;

public class DevelopmentCardData {

    public String id;
    public int victoryPoints;
    public ResourceList price;
    public ResourceList require;
    public ResourceList produce;
    public DevelopmentCard.Color color;


    public DevelopmentCardData(String id, int victoryPoints, ResourceList price, ResourceList require, ResourceList produce, DevelopmentCard.Color color) {
        this.id = id;
        this.victoryPoints = victoryPoints;
        this.price = price;
        this.require = require;
        this.produce = produce;
        this.color = color;
    }
}

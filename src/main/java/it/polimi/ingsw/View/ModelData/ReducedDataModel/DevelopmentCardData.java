package it.polimi.ingsw.View.ModelData.ReducedDataModel;

import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;

import java.util.List;

public class DevelopmentCardData {

    public String id;
    public int victoryPoints;
    public ResourceList price;
    public ResourceList require;
    public ResourceList produce;


    public DevelopmentCardData(String id, int victoryPoints, ResourceList price, ResourceList require, ResourceList produce) {
        this.id = id;
        this.victoryPoints = victoryPoints;
        this.price = price;
        this.require = require;
        this.produce = produce;
    }



}

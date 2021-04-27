package it.polimi.ingsw.Model.ProductionCard;

import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;
import it.polimi.ingsw.Model.ResourceStorage.ResourceStorage;

import java.util.List;

public class ProductionCard {

    private String id;
    private ResourceList require;
    private ResourceList produce;

    public ProductionCard(String id,ResourceList require, ResourceList produce) {
        this.require = require;
        this.produce = produce;
    }


    public String getId() {
        return id;
    }

    public List<Marble> make(ResourceStorage storage){
        return produce.getAll();
    }


}

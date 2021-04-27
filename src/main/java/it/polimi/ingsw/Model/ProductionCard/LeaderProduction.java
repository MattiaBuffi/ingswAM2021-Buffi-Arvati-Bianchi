package it.polimi.ingsw.Model.ProductionCard;

import it.polimi.ingsw.Model.Marble.MarbleColor;
import it.polimi.ingsw.Model.Marble.ResourceList;

public class LeaderProduction {
    private String id;
    private ResourceList require;
    private ResourceList produce;


    public String getId() {
        return id;
    }

    public ProductionCard getCard(MarbleColor color){
        if(color == MarbleColor.RED
                || color == MarbleColor.WHITE ){
            return null;
        }

        ResourceList production = new ResourceList().sum(produce);
        return new ProductionCard(id,require, production);
    }


}

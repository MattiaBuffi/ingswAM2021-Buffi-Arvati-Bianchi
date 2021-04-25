package it.polimi.ingsw.Model.ProductionCard;

import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;

public class LeaderProduction {

    private ResourceList require;
    private ResourceList produce;


    public ProductionCard getCard(Marble marble){
        return new ProductionCard(require, produce.add(marble));
    }


}

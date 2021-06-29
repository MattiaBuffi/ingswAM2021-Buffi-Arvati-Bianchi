package it.polimi.ingsw.Model.ProductionCard;

import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;

public class LeaderProduction {


    private final String id;
    private final ResourceList require;
    private final ResourceList produce;


    public LeaderProduction(String id, Marble.Color require) {
        this.id = id;
        this.require = new ResourceList();
        this.produce = new ResourceList();
        this.require.add(require);
        this.produce.add(Marble.Color.RED);
    }

    public String getId() {
        return id;
    }

    public ProductionCard getCard(Marble.Color color){
        if(color == Marble.Color.RED
                || color == Marble.Color.WHITE ){
            return null;
        }

        ResourceList production = new ResourceList().sum(produce).sum(color);
        return new ProductionCard(id,require, production);
    }


}

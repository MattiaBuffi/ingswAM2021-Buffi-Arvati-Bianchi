package it.polimi.ingsw.Model.ProductionCard;

import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;


/**
 *  Represent the production leader ability. Permit to create a production card when the leader card is activated
 */
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


    /**
     * Generate a production card with the output equal to the one choose by the player
     * @param color output color of the production card
     * @return the production card with the output choose by the player
     */
    public ProductionCard getCard(Marble.Color color){
        if(color == Marble.Color.RED
                || color == Marble.Color.WHITE ){
            return null;
        }

        ResourceList production = new ResourceList().sum(produce).sum(color);
        return new ProductionCard(id,require, production);
    }


}

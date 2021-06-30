package it.polimi.ingsw.Model.ProductionCard;

import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;
import it.polimi.ingsw.Model.ResourceStorage.ResourceStorage;

import java.util.List;

/**
 * Represent a card which can produce resources. Extended by DevelopmentCard and LeaderProduction
 */
public class ProductionCard {

    private String id;
    private ResourceList require;
    private ResourceList produce;

    public ProductionCard(String id, ResourceList require, ResourceList produce) {
        this.id = id;
        this.require = require;
        this.produce = produce;
    }


    public ResourceList getRequire() {
        return new ResourceList().sum(require);
    }

    public ResourceList getProduce() {
        return new ResourceList().sum(produce);
    }

    public String getId() {
        return id;
    }

    /**
     * Take resources from storage and return the result of the production
     * @param storage where to take resource for production
     * @return result of the production
     */
    public List<Marble> make(ResourceStorage storage){
        if(!storage.withdrawal(require)){
            return null;
        }
        return produce.getAllMarble();
    }


}

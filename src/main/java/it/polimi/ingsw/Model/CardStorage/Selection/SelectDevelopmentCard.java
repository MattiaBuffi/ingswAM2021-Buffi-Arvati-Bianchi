package it.polimi.ingsw.Model.CardStorage.Selection;

import it.polimi.ingsw.Model.ProductionCard.ProductionCard;


public class SelectDevelopmentCard implements ProductionSelector {

    private final String id;

    public SelectDevelopmentCard(String id) {
        this.id = id;
    }

    public String getId(){
        return id;
    }

    @Override
    public ProductionCard getCard(ProductionVisitor storage) {
        return storage.visit(this);
    }

}

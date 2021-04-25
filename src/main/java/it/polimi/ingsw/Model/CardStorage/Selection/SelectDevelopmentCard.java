package it.polimi.ingsw.Model.CardStorage.Selection;

import it.polimi.ingsw.Model.ProductionCard.ProductionCard;


public class SelectDevelopmentCard implements ProductionSelector {
    @Override
    public ProductionCard getCard(ProductionVisitor storage) {
        return storage.visit(this);
    }

}

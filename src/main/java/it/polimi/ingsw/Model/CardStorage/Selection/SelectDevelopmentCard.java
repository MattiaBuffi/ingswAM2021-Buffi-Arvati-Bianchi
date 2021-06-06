package it.polimi.ingsw.Model.CardStorage.Selection;

import it.polimi.ingsw.Model.ProductionCard.ProductionCard;


public class SelectDevelopmentCard implements ProductionSelector {

    private final int position;

    public SelectDevelopmentCard(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public ProductionCard getCard(ProductionVisitor storage) {
        return storage.visit(this);
    }

}

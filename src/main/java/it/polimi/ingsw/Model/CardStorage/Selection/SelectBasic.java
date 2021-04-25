package it.polimi.ingsw.Model.CardStorage.Selection;



import it.polimi.ingsw.Model.Marble.ResourceList;
import it.polimi.ingsw.Model.ProductionCard.ProductionCard;

public class SelectBasic implements ProductionSelector {

    private ResourceList requires;
    private ResourceList produce;

    @Override
    public ProductionCard getCard(ProductionVisitor storage) {
        return storage.visit(this);
    }

    public ResourceList getRequires() {
        return requires;
    }

    public ResourceList getProduce() {
        return produce;
    }
}

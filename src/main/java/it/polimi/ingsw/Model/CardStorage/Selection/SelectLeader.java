package it.polimi.ingsw.Model.CardStorage.Selection;

import it.polimi.ingsw.Model.Marble.MarbleColor;
import it.polimi.ingsw.Model.ProductionCard.ProductionCard;

public class SelectLeader implements ProductionSelector {

    private final String id;
    private final MarbleColor selectedColor;

    public SelectLeader(String id, MarbleColor selectedColor) {
        this.id = id;
        this.selectedColor = selectedColor;
    }

    public String getId(){
        return id;
    }

    public MarbleColor getSelectedColor() {
        return selectedColor;
    }

    @Override
    public ProductionCard getCard(ProductionVisitor storage) {
        return storage.visit(this);
    }

}

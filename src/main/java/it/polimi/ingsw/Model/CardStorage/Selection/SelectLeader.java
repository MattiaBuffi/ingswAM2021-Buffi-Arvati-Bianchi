package it.polimi.ingsw.Model.CardStorage.Selection;

import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.ProductionCard.ProductionCard;

public class SelectLeader implements ProductionSelector {

    private final String id;
    private final Marble.Color selectedColor;

    public SelectLeader(String id, Marble.Color selectedColor) {
        this.id = id;
        this.selectedColor = selectedColor;
    }

    public String getId(){
        return id;
    }

    public Marble.Color getSelectedColor() {
        return selectedColor;
    }

    @Override
    public ProductionCard getCard(ProductionVisitor storage) {
        return storage.visit(this);
    }


}

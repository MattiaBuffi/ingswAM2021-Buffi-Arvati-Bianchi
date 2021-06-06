package it.polimi.ingsw.Model.CardStorage.Selection;



import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;
import it.polimi.ingsw.Model.ProductionCard.ProductionCard;

public class SelectBasic implements ProductionSelector {

    private static final String id = "basic";
    private ResourceList requires;
    private ResourceList produce;

    public SelectBasic(Marble.Color input_1, Marble.Color input_2, Marble.Color output) {
        requires = new ResourceList();
        produce = new ResourceList();
        requires.add(input_1);
        requires.add(input_2);
        produce.add(output);
    }

    @Override
    public ProductionCard getCard(ProductionVisitor storage) {
        return storage.visit(this);
    }

    public String getId(){
        return id;
    }

    public ResourceList getRequires() {
        return requires;
    }

    public ResourceList getProduce() {
        return produce;
    }


}

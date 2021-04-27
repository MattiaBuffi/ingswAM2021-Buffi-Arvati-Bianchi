package it.polimi.ingsw.Model.CardStorage.Selection;



import it.polimi.ingsw.Model.ProductionCard.ProductionCard;

public interface ProductionSelector {

    ProductionCard getCard(ProductionVisitor storage);

}

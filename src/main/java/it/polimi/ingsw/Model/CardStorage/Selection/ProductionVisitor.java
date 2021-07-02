package it.polimi.ingsw.Model.CardStorage.Selection;

import it.polimi.ingsw.Model.ProductionCard.ProductionCard;



/**
 * Interface for handling ProductionCard by visitor pattern
 */
public interface ProductionVisitor {

    ProductionCard visit(SelectBasic selector);
    ProductionCard visit(SelectDevelopmentCard selector);
    ProductionCard visit(SelectLeader selector);

}

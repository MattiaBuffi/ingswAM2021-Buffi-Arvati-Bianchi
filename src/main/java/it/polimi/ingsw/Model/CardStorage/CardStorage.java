package it.polimi.ingsw.Model.CardStorage;


import it.polimi.ingsw.Model.CardMarket.PurchasableCard;
import it.polimi.ingsw.Model.CardStorage.Selection.ProductionSelector;
import it.polimi.ingsw.Model.ProductionCard.DevelopmentCard;
import it.polimi.ingsw.Model.ProductionCard.ProductionCard;
import it.polimi.ingsw.Model.ResourceStorage.ResourceStorage;

import java.util.List;

/**
 *  deposito di Development card
 */
public interface CardStorage {
    /**
     *  compra una nuova Development card
     */
    boolean buyCard(PurchasableCard card, int destinationPosition, ResourceStorage storage);

    /**
     *  ritorna una Carta di produzione a seconda del selector
     */
    ProductionCard getCard(ProductionSelector selector);

    /**
     *  ritorna tutte le carte che contiene
     */
    List<DevelopmentCard> getCards();

}

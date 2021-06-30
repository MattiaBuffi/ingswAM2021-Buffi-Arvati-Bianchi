package it.polimi.ingsw.Model.CardStorage;


import it.polimi.ingsw.Model.CardMarket.PurchasableCard;
import it.polimi.ingsw.Model.CardStorage.Selection.ProductionSelector;
import it.polimi.ingsw.Model.ProductionCard.DevelopmentCard;
import it.polimi.ingsw.Model.ProductionCard.ProductionCard;
import it.polimi.ingsw.Model.ResourceStorage.ResourceStorage;

import java.util.List;

/**
 *  Interface which represent the storage of development cards
 */
public interface CardStorage {

    /**
     * Buy a development card
     * @param card Card to buy
     * @param destinationPosition Where to store the card in the storage
     * @param storage Where to store the card bought
     * @return True if the purchase was successful
     */
    boolean buyCard(PurchasableCard card, int destinationPosition, ResourceStorage storage);

    /**
     * Return a development card based on the specified selector
     * @param selector selector for the card to get
     * @return card return by the selector
     */
    ProductionCard getCard(ProductionSelector selector);


    /**
     * Return all the cards contained in the CardStorage
     * @return
     */
    List<DevelopmentCard> getCards();

    /**
     * TODO: REVIEW DOCUMENTATION
     */

}

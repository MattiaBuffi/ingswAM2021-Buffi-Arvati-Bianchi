package it.polimi.ingsw.Model.CardStorage;

import it.polimi.ingsw.Model.CardMarket.PurchasableCard;
import it.polimi.ingsw.Model.CardStorage.Selection.ProductionSelector;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.ProductionCard.DevelopmentCard;
import it.polimi.ingsw.Model.ProductionCard.ProductionCard;
import it.polimi.ingsw.Model.ResourceStorage.DiscountDecorator;
import it.polimi.ingsw.Model.ResourceStorage.ResourceStorage;

import java.util.List;


/**
 *  Decorator to apply a discount during the purchase of a card
 */
public class CardDiscountDecorator implements CardStorage {

    private final CardStorage cardStorage;
    private final Marble.Color discount;

    public CardDiscountDecorator(CardStorage cardStorage, Marble.Color discount) {
        this.cardStorage = cardStorage;
        this.discount = discount;
    }


    /**
     * Buy a development card
     * @param card Card to buy
     * @param position CardSlot where to store the card in the storage
     * @param storage From where to take the resources
     * @return True if the purchase was successful
     */
    @Override
    public boolean buyCard(PurchasableCard card, int position, ResourceStorage storage) {
        return cardStorage.buyCard(card, position, new DiscountDecorator(storage, discount));
    }

    /**
     * Return a development card based on the specified selector
     * @param selector selector for the card to get
     * @return card return by the selector
     */
    @Override
    public ProductionCard getCard(ProductionSelector selector) {
        return cardStorage.getCard(selector);
    }

    /**
     * @return all the cards contained in the CardStorage
     */
    @Override
    public List<DevelopmentCard> getCards() {
        return cardStorage.getCards();
    }


}


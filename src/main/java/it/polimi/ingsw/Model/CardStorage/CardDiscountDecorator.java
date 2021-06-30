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
 *  decoratore che applica uno sconto quando si cerca di comprare una carta
 */
public class CardDiscountDecorator implements CardStorage {

    private final CardStorage cardStorage;
    private final Marble.Color discount;

    public CardDiscountDecorator(CardStorage cardStorage, Marble.Color discount) {
        this.cardStorage = cardStorage;
        this.discount = discount;
    }

    @Override
    public boolean buyCard(PurchasableCard card, int position, ResourceStorage storage) {
        return cardStorage.buyCard(card, position, new DiscountDecorator(storage, discount));
    }

    @Override
    public ProductionCard getCard(ProductionSelector selector) {
        return cardStorage.getCard(selector);
    }

    @Override
    public List<DevelopmentCard> getCards() {
        return cardStorage.getCards();
    }


}


package it.polimi.ingsw.Model.CardStorage;


import it.polimi.ingsw.Model.CardMarket.PurchasableCard;
import it.polimi.ingsw.Model.CardStorage.Selection.*;
import it.polimi.ingsw.Model.ProductionCard.DevelopmentCard;
import it.polimi.ingsw.Model.ProductionCard.LeaderProduction;
import it.polimi.ingsw.Model.ProductionCard.ProductionCard;
import it.polimi.ingsw.Model.ResourceStorage.ResourceStorage;

import java.util.List;

/**
 *  Decorator of CardStorage. It permits the creation of a leader production card when a leader card with the power to produce
 *  is activated.
 */
public class ExtraProductionDecorator implements CardStorage, ProductionVisitor {

    private LeaderProduction production;
    private CardStorage cardStorage;


    public ExtraProductionDecorator(CardStorage cardStorage,LeaderProduction production) {
        this.cardStorage = cardStorage;
        this.production = production;
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
        return cardStorage.buyCard(card, position, storage);
    }

    /**
     * Return a development card based on the specified selector
     * @param selector selector for the card to get
     * @return card return by the selector
     */
    @Override
    public ProductionCard getCard(ProductionSelector selector) {
        return selector.getCard(this);
    }

    /**
     * @return all the cards contained in the CardStorage
     */
    @Override
    public List<DevelopmentCard> getCards() {
        return cardStorage.getCards();
    }


    /**
     * @param selector selector for the card to get
     * @return Custom ProductionCard made by a Basic Production
     */
    @Override
    public ProductionCard visit(SelectBasic selector) {
        return cardStorage.getCard(selector);
    }

    /**
     *
     * @param selector selector for the card to get
     * @return the selected ProductionCard from the Storage
     */
    @Override
    public ProductionCard visit(SelectDevelopmentCard selector) {
        return cardStorage.getCard(selector);
    }

    /**
     * @param selector
     * @return Production Card from the CardStorage if it exists, if not return a Custom Production Card made by Leader Card Power
     */
    @Override
    public ProductionCard visit(SelectLeader selector) {
        if(!selector.getId().equals(production.getId()) ){
            return cardStorage.getCard(selector);
        }
        return production.getCard(selector.getSelectedColor());
    }
}

package it.polimi.ingsw.Model.CardStorage;


import it.polimi.ingsw.Model.CardMarket.PurchasableCard;
import it.polimi.ingsw.Model.CardStorage.Selection.*;
import it.polimi.ingsw.Model.ProductionCard.DevelopmentCard;
import it.polimi.ingsw.Model.ProductionCard.LeaderProduction;
import it.polimi.ingsw.Model.ProductionCard.ProductionCard;
import it.polimi.ingsw.Model.ResourceStorage.ResourceStorage;

import java.util.List;

/**
 *  decoratore di CardStorage, permette a creazione di una Leader Production card
 */
public class ExtraProductionDecorator implements CardStorage, ProductionVisitor {

    private LeaderProduction production;
    private CardStorage cardStorage;


    public ExtraProductionDecorator(CardStorage cardStorage,LeaderProduction production) {
        this.cardStorage = cardStorage;
        this.production = production;
    }

    @Override
    public boolean buyCard(PurchasableCard card, int position, ResourceStorage storage) {
        return cardStorage.buyCard(card, position, storage);
    }

    @Override
    public ProductionCard getCard(ProductionSelector selector) {
        return selector.getCard(this);
    }

    @Override
    public List<DevelopmentCard> getCards() {
        return cardStorage.getCards();
    }


    @Override
    public ProductionCard visit(SelectBasic selector) {

        return cardStorage.getCard(selector);
    }


    @Override
    public ProductionCard visit(SelectDevelopmentCard selector) {

        return cardStorage.getCard(selector);
    }


    @Override
    public ProductionCard visit(SelectLeader selector) {

        if(selector.getId().equals(production.getId()) ){
            return cardStorage.getCard(selector);
        }
        return production.getCard(selector.getSelectedColor());

    }


}

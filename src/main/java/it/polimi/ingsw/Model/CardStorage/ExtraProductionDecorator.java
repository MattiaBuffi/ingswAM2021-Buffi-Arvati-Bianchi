package it.polimi.ingsw.Model.CardStorage;


import it.polimi.ingsw.Model.CardMarket.MarketCard;
import it.polimi.ingsw.Model.CardStorage.Selection.*;
import it.polimi.ingsw.Model.ProductionCard.LeaderProduction;
import it.polimi.ingsw.Model.ProductionCard.ProductionCard;
import it.polimi.ingsw.Model.ResourceStorage.ResourceStorage;

public class ExtraProductionDecorator implements CardStorage, ProductionVisitor {

    private LeaderProduction production;
    private CardStorage cardStorage;
    private final String id;

    public ExtraProductionDecorator(String id) {
        this.id = id;
    }

    @Override
    public void buyCard(MarketCard card, String destId, ResourceStorage storage) {
        cardStorage.buyCard(card,destId, storage);
    }

    @Override
    public ProductionCard getCard(ProductionSelector selector) {
        return selector.getCard(this);
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
        return null;
    }


}

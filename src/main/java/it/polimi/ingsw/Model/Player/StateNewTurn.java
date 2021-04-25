package it.polimi.ingsw.Model.Player;


import it.polimi.ingsw.Model.CardMarket.MarketCard;
import it.polimi.ingsw.Model.CardStorage.Selection.ProductionSelector;
import it.polimi.ingsw.Model.Marble.Marble;

import java.util.List;

public class StateNewTurn extends PlayerState {



    @Override
    protected void invalidAction() {

    }

    @Override
    protected void buyCard(MarketCard card, String destinationId) {
        context.setState(PlayerStateName.BUY_CARD);
        context.buyCard(card, destinationId);
    }

    @Override
    protected void production(ProductionSelector selector) {
        context.setState(PlayerStateName.PRODUCTION);
        context.production(selector);
    }

    @Override
    protected void buyResources(List<Marble> resources) {
        context.setState(PlayerStateName.BUY_RESOURCE);
        context.buyResources(resources);
    }

}
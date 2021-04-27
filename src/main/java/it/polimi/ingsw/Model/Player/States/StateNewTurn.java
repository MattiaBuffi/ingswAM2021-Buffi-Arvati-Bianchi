package it.polimi.ingsw.Model.Player.States;


import it.polimi.ingsw.Model.CardMarket.PurchasableCard;
import it.polimi.ingsw.Model.CardStorage.Selection.ProductionSelector;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Player.PlayerState;

import java.util.List;

public class StateNewTurn extends PlayerState {



    @Override
    protected void invalidAction() {

    }

    @Override
    protected void buyCard(PurchasableCard card, String destinationId) {
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

    @Override
    protected void endTurn(){

    }

}
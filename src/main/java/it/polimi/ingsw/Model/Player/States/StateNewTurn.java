package it.polimi.ingsw.Model.Player.States;


import it.polimi.ingsw.Model.CardMarket.PurchasableCard;
import it.polimi.ingsw.Model.CardStorage.Selection.ProductionSelector;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.PlayerState;

import java.util.List;

public class StateNewTurn extends PlayerState {

    private static StateNewTurn instance;

    private StateNewTurn(Name stateName){
        super(stateName);
    }


    @Override
    protected boolean buyCard(Player context, PurchasableCard card, int destinationId) {
        context.setState(StateBuyCard.get());
        return context.buyCard(card, destinationId);
    }

    @Override
    protected boolean production(Player context, ProductionSelector selector) {
        context.setState(StateProduction.get());
        context.getProductionHandler().setupProductionHandler(context.getCardStorage(), context.getResourceStorage(), context.getVaticanToken());
        return context.production(selector);
    }

    @Override
    protected boolean buyResources(Player context, List<Marble> resources) {
        context.setState(StateBuyResource.get());
        return context.buyResources(resources);
    }



    @Override
    protected boolean discardLeader(Player context, String leaderId) {
        //...
        return true;
    }

    @Override
    protected boolean activateLeader(Player context, String leaderId) {
        //...
        return true;
    }


    @Override
    protected boolean endTurn(Player context){
        //...
        return true;
    }


    public static StateNewTurn get(){
        if(instance == null){
            instance = new StateNewTurn(Name.NEW_TURN);
        }
        return instance;
    }

}
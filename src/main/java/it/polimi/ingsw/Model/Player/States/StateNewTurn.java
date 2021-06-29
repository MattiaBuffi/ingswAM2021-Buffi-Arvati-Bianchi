package it.polimi.ingsw.Model.Player.States;


import it.polimi.ingsw.Message.Model.AvailableLeaderCard;
import it.polimi.ingsw.Message.Model.ErrorUpdate;
import it.polimi.ingsw.Message.Model.LeaderCardActivation;
import it.polimi.ingsw.Model.CardMarket.PurchasableCard;
import it.polimi.ingsw.Model.CardStorage.Selection.ProductionSelector;
import it.polimi.ingsw.Model.LeaderCard.LeaderCard;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.PlayerState;
import it.polimi.ingsw.Model.ResourceStorage.PlayerStorage;

import java.util.List;

public class StateNewTurn extends PlayerState {

    private static StateNewTurn instance;

    private StateNewTurn(Name stateName){
        super(stateName);
    }


    public boolean move(PlayerStorage storage, int originId, int destinationId){
        return storage.move(originId, destinationId);
    }

    @Override
    protected boolean moveResources(Player context, int originId, int destinationId) {
        return move(context.getResourceStorage(), originId, destinationId);
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
        for (LeaderCard lc: context.getLeaderCards()){
            if(!lc.getId().equals(leaderId)){
                continue;
            }

            context.getLeaderCards().remove(lc);
            context.notifyUser(new AvailableLeaderCard(context.getLeaderCards()));

            return true;
        }

        context.notifyUser(new ErrorUpdate("no card found"));
        return false;
    }


    public boolean activateCard(Player player, LeaderCard card){

        if(!card.getRequirementsStrategy().canActivate(player)){
            player.notifyUser(new ErrorUpdate("can't fulfill card requirement"));
            return false;
        }

        card.getActivationStrategy().activate(player, card);
        card.setActive(true);
        return true;
    }


    @Override
    protected boolean activateLeader(Player context, String leaderId) {
        for (LeaderCard lc: context.getLeaderCards()){

            if(!lc.getId().equals(leaderId)){
                continue;
            }

            if(lc.isActive()){
                return false;
            }

            activateCard(context, lc);
            context.notifyAllPlayers(new LeaderCardActivation(lc));
            return true;

        }

        context.notifyUser(new ErrorUpdate("no card found"));
        return false;

    }


    @Override
    protected boolean endTurn(Player context){
        context.notifyUser(new ErrorUpdate( "illegal action"));
        return false;
    }



    public static void setState(Player context){
        context.setState(get());

    }

    public static StateNewTurn get(){
        if(instance == null){
            instance = new StateNewTurn(Name.NEW_TURN);
        }
        return instance;
    }

}
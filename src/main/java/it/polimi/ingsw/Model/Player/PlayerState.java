package it.polimi.ingsw.Model.Player;

import it.polimi.ingsw.Message.Model.ErrorUpdate;
import it.polimi.ingsw.Model.CardMarket.PurchasableCard;
import it.polimi.ingsw.Model.CardStorage.Selection.ProductionSelector;
import it.polimi.ingsw.Model.LeaderCard.LeaderCard;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.ProductionCard.DevelopmentCard;


import java.util.List;

public abstract class PlayerState {

    public interface Context {
        void setState(PlayerState state);
    }


    public enum Name {
        NEW_GAME,
        NEW_TURN,
        WAIT,
        BUY_RESOURCE,
        STORE_RESOURCE,
        BUY_CARD,
        PRODUCTION,
        END_GAME;
    }


    private final Name name;

    public PlayerState(Name name){
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    protected boolean invalidAction(Player player){
        player.notifyUser(new ErrorUpdate( "illegal action"));
        return false;
    }


    protected boolean setActive(Player context){
        return invalidAction(context);
    }

    protected boolean buyCard(Player context, PurchasableCard card, int destinationId){
        return invalidAction(context);
    }

    protected boolean production(Player context, ProductionSelector selector){
        return invalidAction(context);
    }

    protected boolean storeResource(Player context, Marble.Color color, int storageId){
        return invalidAction(context);
    }

    protected boolean buyResources(Player context, List<Marble> resources){
        return invalidAction(context);
    }

    protected boolean moveResources(Player context, int originId, int destinationId){
        return invalidAction(context);
    }

    protected boolean discardLeader(Player context, String leaderId){
        return invalidAction(context);
    }

    protected boolean activateLeader(Player context, String leaderId){
        return invalidAction(context);
    }

    protected boolean endTurn(Player context){
        return invalidAction(context);
    }


    protected int getVictoryPoints(Player context){
        int points = 0;

        //vatican route points
        points += context.getVaticanToken().getVictoryPoints();
        //deleted leader card point
        points += 2 - context.getLeaderCards().size();
        //active leader card point
        for (LeaderCard lc: context.getLeaderCards()){
            points += lc.getVictoryPoints();
        }
        //production card point
        for(DevelopmentCard c: context.getCardStorage().getCards()){
            points += c.getVictoryPoint();
        }
        return points;

    }


}

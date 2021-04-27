package it.polimi.ingsw.Model.Player;

import it.polimi.ingsw.Model.CardMarket.PurchasableCard;
import it.polimi.ingsw.Model.CardStorage.Selection.ProductionSelector;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.MarbleColor;


import java.util.List;

public abstract class PlayerState {

    protected Player context;

    protected abstract void invalidAction();

    protected void buyCard(PurchasableCard card, String destinationId){
        invalidAction();
    }

    protected void production(ProductionSelector selector){
        invalidAction();
    }

    protected void storeResource(MarbleColor color, String storageId){
        invalidAction();
    }

    protected void buyResources(List<Marble> resources){
        invalidAction();
    }

    protected void moveResources(String originId, String destinationId){
        context.resourceStorage.move(originId, destinationId);
    }

    protected void discardLeader(String leaderId){
        /*
        for (LeaderCard c: context.getLeaderCards()){
            if(c.getId() == leaderId){
                context.leaderCards.remove(c);
                //addVictoryPoints
                break;
            }
        }*/
    }

    protected void activateLeader(String leaderId){
        /*
        for (LeaderCard c: context.getLeaderCards()){
            if(c.getId() == leaderId){
                c.activate(context);
                break;
            }
        }*/
    }

    protected void endTurn(){
        invalidAction();
    }


}

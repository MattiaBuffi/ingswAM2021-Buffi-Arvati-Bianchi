package it.polimi.ingsw.Model.Player;

import it.polimi.ingsw.Model.CardMarket.MarketCard;
import it.polimi.ingsw.Model.CardStorage.CardStorage;
import it.polimi.ingsw.Model.CardStorage.Selection.ProductionSelector;
import it.polimi.ingsw.Model.LeaderCard.LeaderCard;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.MarbleColor;
import it.polimi.ingsw.Model.Marble.ResourceList;
import it.polimi.ingsw.Model.ResourceStorage.PlayerStorage;
import it.polimi.ingsw.Model.VaticanRoute.PlayerToken;

import java.util.List;

public class Player {

    private List<LeaderCard> leaderCards;
    private PlayerToken faithToken;
    private PlayerStorage resourceStorage;
    private CardStorage cardStorage;

    private PlayerState state;

    private ResourceList selectableResources;

    public void setState(PlayerStateName stateName){

    }


    public LeaderCard getLeaderById(String id){
        for( LeaderCard c: leaderCards){
            if(c.getId() == id){
                return c;
            }
        }
        return null;
    }

    protected List<LeaderCard> getLeaderCards() {
        return leaderCards;
    }

    public PlayerToken getFaithToken() {
        return faithToken;
    }

    public PlayerStorage getResourceStorage() {
        return resourceStorage;
    }

    public CardStorage getCardStorage() {
        return cardStorage;
    }


    public void buyCard(MarketCard card, String destinationId){
        state.buyCard(card, destinationId);
    }

    public void production(ProductionSelector selector){
        state.production( selector);
    }

    public void storeResource(MarbleColor color, String storageId){
        state.storeResource(color, storageId);
    }

    public void buyResources(List<Marble> resources){
        state.buyResources(resources);
    }

    public void moveResources(String originId, String destinationId){
        state.moveResources(originId, destinationId);
    }

    public void discardLeader(String leaderId){
        state.discardLeader(leaderId);
    }

    public void activateLeader(String leaderId){
        state.activateLeader(leaderId);
    }


}

package it.polimi.ingsw.Model.Player;

import it.polimi.ingsw.Model.CardMarket.PurchasableCard;
import it.polimi.ingsw.Model.CardStorage.PlayerCardStorage;
import it.polimi.ingsw.Model.LeaderCard.LeaderCard;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.MarbleColor;
import it.polimi.ingsw.Model.CardStorage.CardStorage;
import it.polimi.ingsw.Model.CardStorage.Selection.ProductionSelector;
import it.polimi.ingsw.Model.Player.ActionStrategy.BuyCardBase;
import it.polimi.ingsw.Model.Player.ActionStrategy.StoreResourceBase;
import it.polimi.ingsw.Model.Player.MarketBuffer.MarketBuffer;
import it.polimi.ingsw.Model.Player.MarketBuffer.PlayerMarbleHandler;
import it.polimi.ingsw.Model.Player.ActionStrategy.BuyCardStrategy;
import it.polimi.ingsw.Model.Player.ActionStrategy.StoreResourceStrategy;
import it.polimi.ingsw.Model.Player.MarketBuffer.ProductionBuffer;
import it.polimi.ingsw.Model.Player.States.PlayerStateName;
import it.polimi.ingsw.Model.Player.States.StateNewGame;
import it.polimi.ingsw.Model.ResourceStorage.PlayerStorage;
import it.polimi.ingsw.Model.ResourceStorage.PlayerStorageBasic;
import it.polimi.ingsw.Model.VaticanRoute.PlayerToken;

import java.util.List;

public class Player {

    private PlayerState state;
    protected List<LeaderCard> leaderCards;

                                                        // read write
    public PlayerToken faithToken;                      //  yes yes      needed in production and market
    public PlayerStorage resourceStorage;               //  yes yes
    public CardStorage cardStorage;                     //  yes yes


    public PlayerMarbleHandler marketBuffer;
    public PlayerMarbleHandler productionBuffer;


    public BuyCardStrategy buyCardStrategy;             //  yes yes
    public StoreResourceStrategy takeResourceStrategy;  //  yes yes


    public Player(String name, String id, PlayerToken token, List<LeaderCard> leaderCards){
        this.state = new StateNewGame();
        this.resourceStorage = new PlayerStorageBasic();
        this.cardStorage = new PlayerCardStorage();
        this.leaderCards = leaderCards;
        this.marketBuffer = new MarketBuffer();
        this.productionBuffer = new ProductionBuffer();
        this.buyCardStrategy = new BuyCardBase();
        this.takeResourceStrategy = new StoreResourceBase();
    }



    protected void deleteLeaderCard(){

    }


    public void setState(PlayerStateName stateName){

    }



    public void buyCard(PurchasableCard card, String destinationId){
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

    public void endTurn(){
        state.endTurn();
    }

    public int getVictoryPoints(){
        return 0;
    }


}

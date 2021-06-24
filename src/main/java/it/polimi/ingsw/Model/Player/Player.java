package it.polimi.ingsw.Model.Player;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Model.CardMarket.PurchasableCard;
import it.polimi.ingsw.Model.CardStorage.PlayerCardStorage;
import it.polimi.ingsw.Model.EventBroadcaster;
import it.polimi.ingsw.Model.GameHandler;
import it.polimi.ingsw.Model.LeaderCard.LeaderCard;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.CardStorage.CardStorage;
import it.polimi.ingsw.Model.CardStorage.Selection.ProductionSelector;


import it.polimi.ingsw.Model.Player.ResourceMarket.ResourceBuffer;
import it.polimi.ingsw.Model.Player.ResourceMarket.ResourceMarketHandler;
import it.polimi.ingsw.Model.Player.States.StateSetupCard;
import it.polimi.ingsw.Model.ResourceStorage.PlayerStorage;
import it.polimi.ingsw.Model.TurnHandler;
import it.polimi.ingsw.Model.VaticanRoute.VaticanToken;

import java.util.ArrayList;
import java.util.List;

public class Player implements EventBroadcaster, PlayerState.Context {


    private User user;

    private int position;
    private boolean ready;

    private final VaticanToken vaticanToken;
    private final TurnHandler turnHandler;
    private final GameHandler gameHandler;


    private List<LeaderCard> leaderCards;
    private PlayerStorage resourceStorage;
    private CardStorage cardStorage;
    private ResourceMarketHandler resourceMarketBuffer;
    private ProductionHandler productionHandler;

    private PlayerState state;

    private EventBroadcaster globalBroadcaster;



    public Player(User user, int position, VaticanToken token, List<LeaderCard> leaderCards, TurnHandler turnHandler, GameHandler gameHandler, EventBroadcaster broadcaster){

        this.user = user;
        this.position = position;
        this.ready = true;

        this.turnHandler = turnHandler;
        this.vaticanToken = token;
        this.gameHandler = gameHandler;
        this.globalBroadcaster = broadcaster;

        
        this.leaderCards = new ArrayList<>(leaderCards);
        this.resourceStorage = new PlayerStorage(this);
        this.cardStorage = new PlayerCardStorage(this);
        this.resourceMarketBuffer = new ResourceBuffer(this.vaticanToken, this);
        this.productionHandler = new ProductionHandler(this);

        this.state = StateSetupCard.get();

    }


    public boolean setActive(){
        return state.setActive(this);
    }

    public boolean buyCard(PurchasableCard card, int destinationId){
        return state.buyCard(this,card, destinationId);
    }

    public boolean production(ProductionSelector selector){
        return state.production( this,selector);
    }

    public boolean storeResource(Marble.Color color, int storageId){
        return state.storeResource(this,color, storageId);
    }

    public boolean buyResources(List<Marble> resources){
        return state.buyResources(this,resources);
    }

    public boolean moveResources(int originId, int destinationId){
        return state.moveResources(this,originId, destinationId);
    }

    public boolean discardLeader(String leaderId){
        return state.discardLeader(this,leaderId);
    }

    public boolean activateLeader(String leaderId){
        return state.activateLeader(this,leaderId);
    }

    public boolean endTurn(){
        return state.endTurn(this);
    }



    public int getVictoryPoints(){
        return state.getVictoryPoints(this);
    }



    //***********************************GETTER*************************************************************************


    public User getUser() {
        return user;
    }


    public int getPosition() {
        return position;
    }

    public boolean isReady() {
        return ready;
    }


    public List<LeaderCard> getLeaderCards() {
        return leaderCards;
    }

    public VaticanToken getVaticanToken() {
        return vaticanToken;
    }

    public PlayerStorage getResourceStorage() {
        return resourceStorage;
    }

    public CardStorage getCardStorage() {
        return cardStorage;
    }

    public ResourceMarketHandler getResourceMarketBuffer() {
        return resourceMarketBuffer;
    }

    public ProductionHandler getProductionHandler() {
        return productionHandler;
    }


    public TurnHandler getTurnHandler() {
        return turnHandler;
    }

    public GameHandler getGameHandler() {
        return gameHandler;
    }

    //***********************************SETTER*************************************************************************

    @Override
    public void setState(PlayerState state) {
        this.state = state;
    }


    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public void setResourceStorage(PlayerStorage resourceStorage) {
        this.resourceStorage = resourceStorage;
    }

    public void setCardStorage(CardStorage cardStorage) {
        this.cardStorage = cardStorage;
    }

    public void setResourceMarketBuffer(ResourceMarketHandler resourceMarketBuffer) {
        this.resourceMarketBuffer = resourceMarketBuffer;
    }



    @Override
    public void notifyAllPlayers(Message event) {
        globalBroadcaster.notifyAllPlayers(event);
    }

    @Override
    public void notifyUser(Message event) {
        user.notify(event);
    }


}

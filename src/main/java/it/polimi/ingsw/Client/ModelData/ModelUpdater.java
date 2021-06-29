package it.polimi.ingsw.Client.ModelData;

import it.polimi.ingsw.Client.ModelData.ReducedDataModel.LeaderCard;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.Model.*;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.DevelopmentCardData;
import it.polimi.ingsw.Message.ModelEventHandler;

import java.util.HashSet;
import java.util.Set;

public class ModelUpdater implements ModelEventHandler {

    private ViewModel model;

    private Player player;

    public ModelUpdater(ViewModel model){
        this.model = model;
    }

    private void setPlayer(Player player){
        this.player = player;
    }

    private Player getPlayer(){
        return player;
    }


    @Override
    public void handle(VaticanReport event) {
        for (String username: event.getPlayers()){

            System.out.println("VATICAN REPORT OF "+username+"  "+event.getIndex());

            Set<Integer> set = model.vaticanRoute.getVaticanReports(username);
            if(set.isEmpty()){
                model.vaticanRoute.vaticanReport.put(username, new HashSet<>());

            }

            model.vaticanRoute.getVaticanReports(username).add(event.getIndex());

        }
    }

    @Override
    public void handle(VaticanRoutePosition event) {
        model.vaticanRoute.tokenPosition.put(event.getUsername(), event.getPosition());
    }

    @Override
    public void handle(MarketResourceAvailable event) {
        model.resourceMarketBuffer.add(event.getMarble());
    }

    @Override
    public void handle(MarketResourceTaken event) {
        for(Marble m:model.resourceMarketBuffer ){
            if(m.getColor() == event.getColor()){
                model.resourceMarketBuffer.remove(m);
                return;
            }
        }
    }

    @Override
    public void handle(MarketCardUpdate event) {
        model.cardMarket.setCard(event.getX(), event.getY(),
                new DevelopmentCardData(event.getId(),
                event.getVictoryPoints(),
                event.getPrice(),
                event.getProduce(),
                event.getRequire())
        );
    }

    @Override
    public void handle(ProductionBufferUpdate event) {

        model.productionBuffer.addAll(event.getProduced());
        model.usedProduction.add(event.getId());

    }

    @Override
    public void handle(ResourceMarketUpdate event) {
        model.resourceMarket.update(event.getPosition(), event.getMarbles());

    }

    @Override
    public void handle(ResourceMarketExtra event) {
        model.resourceMarket.setBonusMarble(event.getMarble());
    }

    @Override
    public void handle(ActivePlayer event) {
        model.current = model.getPlayer(event.getUsername());
        model.resourceMarketBuffer.clear();
        model.productionBuffer.clear();
    }

    @Override
    public void handle(AvailableLeaderCard event) {
        model.getPlayer(model.myUsername).updateLeaderCards(event.getLeaderCard());
    }

    @Override
    public void handle(PlayersSetup event) {
        model.players.add( new Player(event.getUsername()));
    }





    @Override
    public void handle(ModelUpdate event) {

        //set the player who is doing the action
        setPlayer(model.getPlayer(event.getPlayerUsername()));

        for (Message<ModelEventHandler> e: event.getMessages()){
            e.accept(this);
        }
    }


    @Override
    public void handle(DevelopmentCardBuyUpdate event) {

        getPlayer().buyCard(event.getPosition(),new DevelopmentCardData(event.getId(),
                event.getVictoryPoints(),
                new ResourceList(),
                event.getProduce(),
                event.getRequire())
        );
    }

    @Override
    public void handle(ChestUpdate event) {
        getPlayer().setChest(event.getResources());
    }

    @Override
    public void handle(ShelfUpdate event) {
        getPlayer().updateShelf(event.getPosition(), event.getMaxSize(), event.getSize(), event.getColor());
    }

    @Override
    public void handle(LeaderCardActivation event) {

        if(getPlayer().getUsername() == model.myUsername){
            for (LeaderCard card : getPlayer().getLeaderCard()){
                if(card.getId() == event.getLeaderCard().getId()){
                    card.setActive(true);
                }
            }
        } else {
            getPlayer().getLeaderCard().add(event.getLeaderCard());
        }

    }






    //*******************currently these method does nothing*************************************************

    @Override
    public void handle(ResourceSetup event) {
        //doNothing
    }

    @Override
    public void handle(ActionTokenPlayed event) {
        //do nothing
    }

    @Override
    public void handle(ErrorUpdate error) {
        //do nothing
    }

    @Override
    public void handle(GameSizeRequest event) {
        //do nothing
    }

    @Override
    public void handle(UsernameSelected event) {
        //do nothing
    }

    @Override
    public void handle(WaitingPlayersUpdate event) {
        //do nothing
    }



}

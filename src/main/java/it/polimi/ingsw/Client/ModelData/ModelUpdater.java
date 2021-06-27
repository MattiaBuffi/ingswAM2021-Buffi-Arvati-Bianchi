package it.polimi.ingsw.Client.ModelData;

import it.polimi.ingsw.Client.ModelData.ReducedDataModel.LeaderCard;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.Model.*;
import it.polimi.ingsw.Model.Marble.ResourceList;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.DevelopmentCardData;
import it.polimi.ingsw.Message.ModelEventHandler;

import java.util.List;

public class ModelUpdater implements ModelEventHandler {

    private ViewModel model;

    public ModelUpdater(ViewModel model){
        this.model = model;
    }

    @Override
    public void handle(VaticanReport event) {

    }

    @Override
    public void handle(VaticanRoutePosition event) {
        model.vaticanRoute.tokenPosition.put(event.getUsername(), event.getPosition());
    }

    @Override
    public void handle(ChestUpdate event) {
        model.current.addToChest(event.getResources());
    }

    @Override
    public void handle(ShelfUpdate event) {
        model.current.updateShelf(event.getPosition(), event.getMaxSize(), event.getSize(), event.getColor());
    }

    @Override
    public void handle(DevelopmentCardBuyUpdate event) {

        model.current.buyCard(event.getPosition(),new DevelopmentCardData(event.getId(),
                        event.getVictoryPoints(),
                        new ResourceList(),
                        event.getProduce(),
                        event.getRequire())
        );
    }

    @Override
    public void handle(MarketResourceAvailable event) {
        model.resourceMarketBuffer.add(event.getMarble());
    }

    @Override
    public void handle(MarketResourceTaken event) {
        model.resourceMarketBuffer.remove(event.getColor());
    }


    @Override
    public void handle(MarketCardUpdate event) {
        model.cardMarket.setCard(event.getX(), event.getY(), new DevelopmentCardData(event.getId(),
                event.getVictoryPoints(),
                event.getPrice(),
                event.getProduce(),
                event.getRequire())
        );
    }



    @Override
    public void handle(ModelUpdate event) {
        for (Message<ModelEventHandler> e: event.getMessages()){
            e.accept(this);
        }
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
    public void handle(LeaderCardActivation event) {

    }

    @Override
    public void handle(ActivePlayer event) {
        model.current = model.getPlayer(event.getUsername());
    }

    @Override
    public void handle(AvailableLeaderCard event) {
        List<LeaderCard> cards = model.getPlayer(model.myUsername).getLeaderCard();
        //doSomething
    }

    @Override
    public void handle(PlayersSetup event) {
        model.players.add( new Player(event.getUsername()));
    }

    @Override
    public void handle(ResourceSetup event) {

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

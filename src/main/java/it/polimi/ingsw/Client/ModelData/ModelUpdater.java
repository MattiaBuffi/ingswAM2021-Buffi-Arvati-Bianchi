package it.polimi.ingsw.Client.ModelData;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.Model.*;
import it.polimi.ingsw.Model.Marble.ResourceList;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.DevelopmentCardData;
import it.polimi.ingsw.Message.ModelEventHandler;

public class ModelUpdater implements ModelEventHandler {

    private ViewModel model;

    public ModelUpdater(ViewModel model){
        this.model = model;
    }

    @Override
    public void handle(VaticanReport vaticanReport) {

    }

    @Override
    public void handle(VaticanRoutePosition vaticanRoutePosition) {

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
    public void handle(ErrorUpdate error) {
        //
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
    public void handle(LeaderCardActivation leaderCardActivation) {

    }


    @Override
    public void handle(GameSizeRequest event) {

    }

    @Override
    public void handle(UsernameSelected event) {

    }

    @Override
    public void handle(WaitingPlayersUpdate event) {

    }


}

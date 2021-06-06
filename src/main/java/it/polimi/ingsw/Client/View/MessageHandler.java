package it.polimi.ingsw.Client.View;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.Model.*;
import it.polimi.ingsw.Model.Marble.ResourceList;
import it.polimi.ingsw.Client.View.ModelData.ReducedDataModel.DevelopmentCardData;
import it.polimi.ingsw.Client.View.ModelData.ReducedDataModel.Shelf;
import it.polimi.ingsw.Client.View.ModelData.ViewModel;
import it.polimi.ingsw.Message.ModelEventHandler;

public class MessageHandler implements ModelEventHandler {

    private ViewModel model;
    private View view;


    @Override
    public void handle(ChestUpdate event) {
        model.current.getChest().addAll(event.getResources());
    }



    @Override
    public void handle(DevelopmentCardBuyUpdate event) {

        model.current.getProductions().get(event.getPosition()).add(new DevelopmentCardData(event.getId(),
                        event.getVictoryPoints(),
                        new ResourceList(),
                        event.getProduce(),
                        event.getRequire())
        );


    }



    @Override
    public void handle(ErrorUpdate error) {

        //view.doSomething()
    }



    @Override
    public void handle(MarketResourceAvailable event) {
        model.resourceMarketBuffer.add(event.getColor());

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
        //view.updateScreen();
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
    public void handle(ShelfUpdate event) {
        model.current.getShelves().add(event.getPosition(), new Shelf(event.getPosition(), event.getMaxSize(), event.getSize(), event.getColor()));

    }




}

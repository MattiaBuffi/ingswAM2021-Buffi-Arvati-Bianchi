package it.polimi.ingsw.Client.GUI;

import it.polimi.ingsw.Message.Model.*;
import it.polimi.ingsw.Message.ModelEventHandler;
import it.polimi.ingsw.Model.Marble.Marble;

import java.util.ArrayList;
import java.util.List;

public class ViewChangesHandler implements ModelEventHandler {
    @Override
    public void handle(ChestUpdate event) {
        ControllerManager.updateChest();
    }

    @Override
    public void handle(DevelopmentCardBuyUpdate event) {

    }

    @Override
    public void handle(MarketResourceAvailable event) {

    }

    @Override
    public void handle(MarketResourceTaken marketResourceTaken) {

    }

    @Override
    public void handle(MarketCardUpdate event) {
        ControllerManager.updateCardMarket(event.getX(), event.getY(), Integer.parseInt(event.getId()));
    }

    @Override
    public void handle(ProductionBufferUpdate event) {

    }

    @Override
    public void handle(ResourceMarketUpdate event) {
        List<Marble.Color> colors = new ArrayList<>();
        for(Marble c: event.getMarbles()){
            colors.add(c.getColor());
        }
        ControllerManager.updateResourceMarket(colors, event.getPosition());
    }

    @Override
    public void handle(ShelfUpdate event) {
        ControllerManager.updateShelves();
    }

    @Override
    public void handle(ResourceMarketExtra resourceMarketExtra) {

    }

    @Override
    public void handle(ErrorUpdate error) {}

    @Override
    public void handle(ModelUpdate event) {}
}

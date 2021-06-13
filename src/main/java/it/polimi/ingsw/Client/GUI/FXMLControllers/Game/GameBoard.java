package it.polimi.ingsw.Client.GUI.FXMLControllers.Game;

import it.polimi.ingsw.Client.GUI.Layout;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.Model.*;
import it.polimi.ingsw.Message.ModelEventHandler;
import javafx.scene.control.Tab;


public class GameBoard implements Layout, ModelEventHandler {


    public Tab productionTab;
    public ProductionTab productionTab_Controller;

    public Tab resourceMarketTab;
    public ResourceMarketTab resourceMarket_Controller;

    public Tab cardsMarketTab;
    public CardsMarketTab cardsMarketTab_Controller;

    public Tab storageTab;
    public StorageTab storageTab_Controller;

    public Tab leaderCardsTab;
    public LeaderCardsTab leaderCardsTab_Controller;

    public Tab scoreboardTab;
    public ScoreboardTab scoreboardTab_Controller;




    private ViewBackEnd backEnd;

    @Override
    public void setup(ViewBackEnd backEnd) {
        System.out.println("GameBoard");
        this.backEnd = backEnd;
        backEnd.setEventHandler(this);

        productionTab_Controller.setup(backEnd);
        resourceMarket_Controller.setup(backEnd);
    }






    @Override
    public void handle(ChestUpdate event) {
        backEnd.getModel().updateModel(event);
    }

    @Override
    public void handle(DevelopmentCardBuyUpdate event) {
        backEnd.getModel().updateModel(event);
    }

    @Override
    public void handle(ErrorUpdate error) {

    }

    @Override
    public void handle(MarketResourceAvailable event) {
        backEnd.getModel().updateModel(event);
    }

    @Override
    public void handle(MarketResourceTaken event) {
        backEnd.getModel().updateModel(event);
    }

    @Override
    public void handle(MarketCardUpdate event) {
        backEnd.getModel().updateModel(event);

        cardsMarketTab_Controller.updateCard(event.getX(), event.getY(), Integer.parseInt(event.getId()));
    }

    @Override
    public void handle(ModelUpdate event) {

    }

    @Override
    public void handle(ProductionBufferUpdate event) {
        backEnd.getModel().updateModel(event);
    }

    @Override
    public void handle(ResourceMarketUpdate event) {
        backEnd.getModel().updateModel(event);
    }

    @Override
    public void handle(ShelfUpdate event) {
        backEnd.getModel().updateModel(event);
    }

    @Override
    public void handle(ResourceMarketExtra event) {
        backEnd.getModel().updateModel(event);
    }






}

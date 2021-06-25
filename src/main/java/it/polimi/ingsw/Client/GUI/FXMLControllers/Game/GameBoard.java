package it.polimi.ingsw.Client.GUI.FXMLControllers.Game;

import it.polimi.ingsw.Client.GUI.FXMLControllers.PopUp.PopUpManager;
import it.polimi.ingsw.Client.GUI.Layout;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.Model.*;
import it.polimi.ingsw.Message.ModelEventHandler;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;

import java.io.IOException;


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

    public Pane vaticanRoutePane;
    public VaticanRoutePane vaticanRoute_Controller;

    private ViewBackEnd backEnd;
    private boolean resourceBufferUpdate = false;

    @Override
    public void setup(ViewBackEnd backEnd) {
        System.out.println("GameBoard");
        this.backEnd = backEnd;
        backEnd.setEventHandler(this);

        productionTab_Controller.setup(backEnd);
        resourceMarket_Controller.setup(backEnd);
        storageTab_Controller.setup(backEnd);
        resourceMarket_Controller.setup(backEnd);
        leaderCardsTab_Controller.setup(backEnd);
        vaticanRoute_Controller.setup(backEnd);
    }


    @Override
    public void handle(ChestUpdate event) {
        backEnd.getModel().updateModel(event);

        storageTab_Controller.updateChest();
    }

    @Override
    public void handle(DevelopmentCardBuyUpdate event) {
        backEnd.getModel().updateModel(event);

        productionTab_Controller.showDevCard(event.getPosition(), event.getId());
    }

    @Override
    public void handle(ErrorUpdate error) {
        try {
            PopUpManager.showErrorPopUp(error.getErrorMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(MarketResourceAvailable event) {
        backEnd.getModel().updateModel(event);
        resourceBufferUpdate = true;
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
        for (Message<ModelEventHandler> e: event.getMessages()){
            e.accept(this);
        }
        if(resourceBufferUpdate){
            storageTab_Controller.manageResourceBuffer();
            resourceBufferUpdate = false;
        }
        scoreboardTab_Controller.updateScoreboard();
    }

    @Override
    public void handle(ProductionBufferUpdate event) {
        backEnd.getModel().updateModel(event);
    }

    @Override
    public void handle(ResourceMarketUpdate event) {
        backEnd.getModel().updateModel(event);

        resourceMarket_Controller.updateMarket(event.getMarbles(), event.getPosition());
    }

    @Override
    public void handle(ShelfUpdate event) {
        backEnd.getModel().updateModel(event);

        storageTab_Controller.updateShelves();
    }

    @Override
    public void handle(ResourceMarketExtra event) {
        backEnd.getModel().updateModel(event);

        resourceMarket_Controller.updateBonusMarble(event.getMarble().getColor());
    }

    @Override
    public void handle(LeaderCardActivation event) {
        //backEnd.something

        leaderPowerSelector(event.getId());
    }

    @Override
    public void handle(VaticanReport event) {
        //backEnd.something

        vaticanRoute_Controller.activatePopeFavor(event.getIndex());
    }

    @Override
    public void handle(VaticanRoutePosition event) {
        //backEnd.something

        vaticanRoute_Controller.updateCross(event.getUsername(), event.getPosition());
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

    private void leaderPowerSelector(String s){
        int id = Integer.parseInt(s);
        if(id < 5) {
            cardsMarketTab_Controller.showLeaderPower(s);
        } else if(id < 9){
            storageTab_Controller.showLeaderPower(s);
        } else if(id <13){
            resourceMarket_Controller.showLeaderPower(s);
        } else {
            productionTab_Controller.showLeaderPower(s);
        }
    }
}

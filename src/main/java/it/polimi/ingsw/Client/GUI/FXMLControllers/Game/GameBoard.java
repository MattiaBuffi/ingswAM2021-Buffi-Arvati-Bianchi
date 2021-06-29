package it.polimi.ingsw.Client.GUI.FXMLControllers.Game;

import it.polimi.ingsw.Client.GUI.FXMLControllers.PopUp.PopUpManager;
import it.polimi.ingsw.Client.GUI.Layout;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.LeaderCard;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.Model.*;
import it.polimi.ingsw.Message.ModelEventHandler;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller of the game_board.fxml. The class has a reference to all the tab and pane contained in it and works as message handler
 * for the message coming from Model. Every ModelUpdate will be forwarded to the backend where a ModelUpdater will update the light model.
 * After every ModelUpdate the tabs of the game board will be updated to show the new data.
 */
public class GameBoard extends ModelEventHandler.Default implements Layout {


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

    private List<String> leaderActivated;
    private boolean setupGame = true;

    @Override
    public void setup(ViewBackEnd backEnd) {
        System.out.println("GameBoard");
        this.backEnd = backEnd;
        backEnd.setEventHandler(this);

        leaderActivated = new ArrayList<>();

        productionTab_Controller.setup(backEnd);
        resourceMarket_Controller.setup(backEnd);
        storageTab_Controller.setup(backEnd);
        cardsMarketTab_Controller.setup(backEnd);
        resourceMarket_Controller.setup(backEnd);
        leaderCardsTab_Controller.setup(backEnd);
        vaticanRoute_Controller.setup(backEnd);
        scoreboardTab_Controller.setup(backEnd);
    }


    @Override
    public void invalidMessage() {}

    @Override
    public void handle(ModelUpdate event) {

        for(Message<ModelEventHandler> message: event.getMessages()){
            message.accept(this);
        }

        updateTabs(event);
    }

    @Override
    public void handle(ErrorUpdate error) {

        try {
            PopUpManager.showErrorPopUp(error.getErrorMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        storageTab_Controller.manageResourceBuffer();
    }

    @Override
    public void handle(AvailableLeaderCard event) {
        if(setupGame) {
            try {
                PopUpManager.showLeaderCardsPopUp(event.getLeaderCard(), leaderCardsTab_Controller);
            } catch (IOException e) {
                e.printStackTrace();
            }
            setupGame = false;
        } else {
            leaderCardsTab_Controller.update();
            checkLeaderCardActivation();
        }
    }

    @Override
    public void handle(ResourceSetup event) {
        try {
            PopUpManager.showStartingResourcesPopUp(event.getAvailableResources(), storageTab_Controller);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(ActivePlayer event) {
        try {
            PopUpManager.showNewTurnPopUp(event.getUsername(), backEnd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        storageTab_Controller.cleanDiscarded();
    }

    @Override
    public void handle(LeaderCardActivation event) {
        leaderCardsTab_Controller.update();
        checkLeaderCardActivation();
    }

    @Override
    public void handle(ActionTokenPlayed event) {
        try {
            PopUpManager.showActionTokenPopUp(event.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateTabs(ModelUpdate event){

        if(event.getPlayerUsername() != null && event.getPlayerUsername().equals(backEnd.getMyUsername())){
            storageTab_Controller.update();
        }

        cardsMarketTab_Controller.update();
        resourceMarket_Controller.update();
        vaticanRoute_Controller.update();
        scoreboardTab_Controller.update();
        productionTab_Controller.update();

        System.out.println("==> Model UPDATE");
    }

    private void leaderPowerSelector(String s){
        int id = Integer.parseInt(s.substring(3));
        System.out.println("SELECTOR: " + id);
        if(id < 5) {
            cardsMarketTab_Controller.showLeaderPower(String.valueOf(id));
        } else if(id < 9){
            storageTab_Controller.showLeaderPower(String.valueOf(id));
        } else if(id <13){
            resourceMarket_Controller.showLeaderPower(String.valueOf(id));
        } else {
            productionTab_Controller.showLeaderPower(String.valueOf(id));
        }
    }

    private void checkLeaderCardActivation(){
        List<LeaderCard> leaderCards = backEnd.getModel().getPlayer(backEnd.getMyUsername()).getLeaderCard();
        System.out.println("CardActivated: " + leaderActivated);

        for(LeaderCard card: leaderCards){
            System.out.println("ID: " + card.getId() + " - isActive: " + card.isActive());
            if(card.isActive() && !leaderActivated.contains(card.getId())){
                leaderPowerSelector(card.getId());
                leaderActivated.add(card.getId());
            }
        }
    }
}

package it.polimi.ingsw.Client.GUI.FXMLControllers.Game;

import it.polimi.ingsw.Client.GUI.FXMLControllers.PopUp.PopUpManager;
import it.polimi.ingsw.Client.GUI.Layout;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.LeaderCard;
import it.polimi.ingsw.Client.ModelData.ViewModel;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.Model.*;
import it.polimi.ingsw.Message.ModelEventHandler;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.List;


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

    @Override
    public void setup(ViewBackEnd backEnd) {
        System.out.println("GameBoard");
        this.backEnd = backEnd;
        backEnd.setEventHandler(this);

        backEnd.setModel(new ViewModel(backEnd.getMyUsername()));

        productionTab_Controller.setup(backEnd);
        resourceMarket_Controller.setup(backEnd);
        storageTab_Controller.setup(backEnd);
        cardsMarketTab_Controller.setup(backEnd);
        resourceMarket_Controller.setup(backEnd);
        leaderCardsTab_Controller.setup(backEnd);
        vaticanRoute_Controller.setup(backEnd);
    }


    @Override
    public void invalidMessage() {

    }

    @Override
    public void handle(ModelUpdate event) {

        backEnd.getModel().updateModel(event);

        updateTabs();
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

    private void updateTabs(){

        cardsMarketTab_Controller.update();
        //leaderCardsTab_Controller.update();
        checkLeaderCardActivation();
        resourceMarket_Controller.update();

        //storageTab_Controller.update();

        //scoreboardTab_Controller.update();

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

    private void checkLeaderCardActivation(){
        List<LeaderCard> leaderCards = backEnd.getModel().getPlayer(backEnd.getMyUsername()).getLeaderCard();

        for(LeaderCard card: leaderCards){
            if(card.isActive() && !leaderActivated.contains(card.getId())){
                leaderPowerSelector(card.getId());
                leaderActivated.add(card.getId());
            }
        }
    }
}

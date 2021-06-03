package it.polimi.ingsw.Model;


import it.polimi.ingsw.Model.ActionTokens.ActionDeck;
import it.polimi.ingsw.Model.VaticanRoute.VaticanRoute;
import it.polimi.ingsw.ServerModel.User;

import java.util.List;

public class Game {

    //private List<Player> player;
    //private CardMarket cardMarket;
    private final ResourceMarket resourceMarket;
    private final VaticanRoute vaticanRoute;
    private final ActionDeck actionDeck;

    public Game(List<User> players) {
        int playersNumber = players.size();
        resourceMarket = new ResourceMarket();
        vaticanRoute = new VaticanRoute(playersNumber);
        this.actionDeck = new ActionDeck(this);
    }

    public Game(int playersNumber) {
        resourceMarket = new ResourceMarket();
        vaticanRoute = new VaticanRoute(playersNumber);
        this.actionDeck = new ActionDeck(this);
    }

    private void giveLeaderCard(){

    }

    public void buyCard(){}

    public void buyResources(){}

    public void storeResource(){}

    public void basicProduction(){}

    public void cardProduction(){}

    public void leaderProduction(){}

    public void moveResources(){}

    public void activateLeaderCard(){}

    public void discardLeaderCard(){}

    public VaticanRoute getVaticanRoute() {
        return vaticanRoute;
    }
}

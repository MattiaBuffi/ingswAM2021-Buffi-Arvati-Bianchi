package it.polimi.ingsw.Client.ModelData;


import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {

    public List<Player> players;
    public Player current;

    public List<Marble> resourceMarketBuffer;
    public ResourceList productionBuffer;
    public List<String> usedProduction;

    public CardMarket cardMarket;
    public ResourceMarket resourceMarket;

    public boolean singlePlayer;

    private ModelUpdater updater;

    public ViewModel(){
        this.players = new ArrayList<>();
        this.resourceMarketBuffer = new ArrayList<>();
        this.productionBuffer = new ResourceList();
        this.usedProduction = new ArrayList<>();
        this.cardMarket = new CardMarket();
        this.resourceMarket = new ResourceMarket();

        this.updater = new ModelUpdater(this);
    }

    public void updateModel(Message<ModelEventHandler> message){
        message.accept(updater);
    }

    public Player getPlayer(String username){
        Player playerToReturn = null;
        for(Player p: players){
            if(p.getUsername().equals(username)){
                playerToReturn = p;
                break;
            }
        }
        return playerToReturn;
    }

}

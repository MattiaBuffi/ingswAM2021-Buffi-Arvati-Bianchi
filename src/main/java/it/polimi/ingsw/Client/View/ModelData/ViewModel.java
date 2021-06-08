package it.polimi.ingsw.Client.View.ModelData;


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


    public ViewModel(){
        this.players = new ArrayList<>();
        this.resourceMarketBuffer = new ArrayList<>();
        this.usedProduction = new ArrayList<>();
        this.cardMarket = new CardMarket();
        this.resourceMarket = new ResourceMarket();
    }

}

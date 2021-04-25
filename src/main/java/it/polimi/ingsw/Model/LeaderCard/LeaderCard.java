package it.polimi.ingsw.Model.LeaderCard;

import it.polimi.ingsw.Model.Player.Player;

public abstract class LeaderCard {

    private String id;
    private int victoryPoints;
    private Boolean ready;


    public abstract void activate(Player player);

    public String getId() {
        return id;
    }

    protected void setReady(Boolean state){
        ready = state;
    }

}

package it.polimi.ingsw.Client.View.ModelData;

import it.polimi.ingsw.Model.Marble.ResourceList;
import it.polimi.ingsw.Client.View.ModelData.ReducedDataModel.DevelopmentCardData;
import it.polimi.ingsw.Client.View.ModelData.ReducedDataModel.LeaderCard;
import it.polimi.ingsw.Client.View.ModelData.ReducedDataModel.Shelf;


import java.util.ArrayList;
import java.util.List;

public class Player {

    private String username;
    private List<LeaderCard> leaderCard;
    private List<List<DevelopmentCardData>> productions;
    private int victoryPoints;
    private int faithPoints;

    private List<Shelf> shelves;
    private ResourceList chest;

    private Player(){
        this.productions = new ArrayList<>();
        this.shelves = new ArrayList<>();
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<LeaderCard> getLeaderCard() {
        return leaderCard;
    }

    public void setLeaderCard(List<LeaderCard> leaderCard) {
        this.leaderCard = leaderCard;
    }

    public List<List<DevelopmentCardData>> getProductions() {
        return productions;
    }

    public void setProductions(List<List<DevelopmentCardData>> productions) {
        this.productions = productions;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public int getFaithPoints() {
        return faithPoints;
    }

    public void setFaithPoints(int faithPoints) {
        this.faithPoints = faithPoints;
    }

    public List<Shelf> getShelves() {
        return shelves;
    }

    public void setShelves(List<Shelf> shelves) {
        this.shelves = shelves;
    }

    public ResourceList getChest() {
        return chest;
    }

    public void setChest(ResourceList chest) {
        this.chest = chest;
    }


}

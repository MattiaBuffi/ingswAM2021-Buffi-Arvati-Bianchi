package it.polimi.ingsw.Client.ModelData;

import it.polimi.ingsw.Client.ModelData.ReducedDataModel.DevelopmentCard;


import java.util.List;

public class Player {

    private String username;
    private List<String> leaderCard;
    private List<List<DevelopmentCard>> productions;
    private int victoryPoints;
    private int faithPoints;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getLeaderCard() {
        return leaderCard;
    }

    public void setLeaderCard(List<String> leaderCard) {
        this.leaderCard = leaderCard;
    }

    public List<List<DevelopmentCard>> getProductions() {
        return productions;
    }

    public void setProductions(List<List<DevelopmentCard>> productions) {
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
}

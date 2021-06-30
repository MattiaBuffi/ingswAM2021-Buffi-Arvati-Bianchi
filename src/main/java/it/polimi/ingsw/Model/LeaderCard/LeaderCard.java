package it.polimi.ingsw.Model.LeaderCard;


import it.polimi.ingsw.Model.LeaderCard.ActivationStrategy.ActivationStrategy;
import it.polimi.ingsw.Model.LeaderCard.RequirementsStrategy.RequirementStrategy;


public class LeaderCard {

    protected String id;
    private int victoryPoints;
    private RequirementStrategy requirementsStrategy;
    private ActivationStrategy activationStrategy;
    private boolean active;

    public LeaderCard(String id, int victoryPoints, RequirementStrategy requirementsStrategy, ActivationStrategy activationStrategy) {
        this.id = id;
        this.victoryPoints = victoryPoints;
        this.requirementsStrategy = requirementsStrategy;
        this.activationStrategy = activationStrategy;
        this.active = false;
    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    public String getId() {
        return id;
    }

    public int getVictoryPoints() {
        return  victoryPoints;
    }

    public RequirementStrategy getRequirementsStrategy() {
        return requirementsStrategy;
    }

    public ActivationStrategy getActivationStrategy() {
        return activationStrategy;
    }
}

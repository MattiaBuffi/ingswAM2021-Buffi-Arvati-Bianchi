package it.polimi.ingsw.Model.LeaderCard;


import it.polimi.ingsw.Model.Player.Player;

public abstract class LeaderCard {

    protected String id;
    private int victoryPoints;
    private boolean active;
    private RequirementStrategy requirementsStrategy;
    private ActivationStrategy activationStrategy;

    public LeaderCard(String id, int victoryPoints, RequirementStrategy requirementsStrategy) {
        this.id = id;
        this.victoryPoints = victoryPoints;
        this.requirementsStrategy = requirementsStrategy;
        active = false;
    }

    public String getId() {
        return id;
    }


    public void activate(Player player){
        if(active){
            return;
        }
        if(!requirementsStrategy.checkRequirements(player)){
            return;
        }
        activationStrategy.execute(player);
        active = true;
    }


    public int getVictoryPoints() {
        if(active){
            return victoryPoints;
        } else {
            return 0;
        }
    }


}

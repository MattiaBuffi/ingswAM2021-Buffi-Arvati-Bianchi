package it.polimi.ingsw.Model.VaticanRoute;

public class BlackCrossToken implements FaithToken {
    private int faithPoints;


    public BlackCrossToken() {
        this.faithPoints = 0;
    }

    @Override
    public void advance() {
        faithPoints++;
    }

    public void advance(int pos){
        faithPoints += pos;
    }

    @Override
    public int getFaithPoints() {
        return faithPoints;
    }

    @Override
    public int getVictoryPoints() {
        return 0;
    }

    @Override
    public void setFaithPoints(int faithPoints) {

    }

    @Override
    public void addVictoryPoints(int victoryPoints) {

    }

    @Override
    public boolean playerWin() {
        return false;
    }

}

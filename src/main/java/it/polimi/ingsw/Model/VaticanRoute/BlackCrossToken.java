package it.polimi.ingsw.Model.VaticanRoute;

public class BlackCrossToken implements FaithToken {
    private int faithPoints;
    private VaticanRoute route;


    public BlackCrossToken(VaticanRoute route) {
        this.faithPoints = 0;
        this.route = route;
    }

    @Override
    public void advance() {
        faithPoints++;
        route.checkPopeSpace(this);
    }

    public void advance(int pos) {
        faithPoints += pos;
        route.checkPopeSpace(this);
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

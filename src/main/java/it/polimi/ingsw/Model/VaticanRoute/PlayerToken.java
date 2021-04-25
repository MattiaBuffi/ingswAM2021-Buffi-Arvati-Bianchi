package it.polimi.ingsw.Model.VaticanRoute;


public class PlayerToken implements FaithToken {

    private int faithPoints;
    private int victoryPoints;
    private VaticanRoute route;

    public PlayerToken(VaticanRoute route) {
        this.faithPoints = 0;
        this.victoryPoints = 0;
        this.route = route;
    }

    @Override
    public void advance() {
        faithPoints++;
        route.checkPopeSpace(this);
    }

    public void concede() {
        for(FaithToken t: route.getTokenList()){
            if(!(t == this)){
                t.advance();
            }
        }
    }

    @Override
    public boolean playerWin() {
        return true;
    }

    public int getFaithPoints() {
        return faithPoints;
    }

    public void setFaithPoints(int faithPoints) {
        this.faithPoints = faithPoints;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public void addVictoryPoints(int victoryPoints) {
        this.victoryPoints += victoryPoints;
    }
}

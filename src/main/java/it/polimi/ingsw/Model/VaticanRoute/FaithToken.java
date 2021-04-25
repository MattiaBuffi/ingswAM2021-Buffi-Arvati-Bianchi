package it.polimi.ingsw.Model.VaticanRoute;

public interface FaithToken {
    void advance();

    int getFaithPoints();

    int getVictoryPoints();

    void setFaithPoints(int faithPoints);

    void addVictoryPoints(int victoryPoints);

    boolean playerWin();
}

package it.polimi.ingsw.Model.VaticanRoute;

public interface FaithToken {
    void advance();

    void advance(int pos);

    int getFaithPoints();

    int getVictoryPoints();

    void setFaithPoints(int faithPoints);

    void addVictoryPoints(int victoryPoints);

    boolean playerWin();
}

package it.polimi.ingsw.Model.TestData;

import it.polimi.ingsw.Model.GameHandler;

public class testHandler implements GameHandler {

    public boolean ended;
    public boolean endTurn;
    public boolean started;

    public testHandler(){
        ended = false;
        started = false;
        endTurn = false;
    }

    @Override
    public void startGame() {
        started = true;
    }

    @Override
    public void endTurn() {
        endTurn = true;
    }

    @Override
    public void endGame() {
        ended = true;
    }

}

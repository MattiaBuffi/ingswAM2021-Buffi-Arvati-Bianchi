package it.polimi.ingsw.Model.TestData;

import it.polimi.ingsw.Model.GameHandler;

public class testHandler implements GameHandler {

    public boolean ended;
    public boolean started;

    public testHandler(){
        ended = false;
        started = false;
    }

    @Override
    public void startGame() {
        started = true;
    }

    @Override
    public void endGame() {
        ended = true;
    }

}

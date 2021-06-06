package it.polimi.ingsw.Model.TestData;

import it.polimi.ingsw.Model.GameTerminator;

public class testTerminator implements GameTerminator {

    public boolean ended;

    public testTerminator(){
        ended = false;
    }

    @Override
    public void endGame() {
        ended = true;
    }

}

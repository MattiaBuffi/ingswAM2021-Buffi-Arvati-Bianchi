package it.polimi.ingsw.Model.ActionTokens;

import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.VaticanRoute.VaticanRoute;

public class BlackCrossActionToken implements ActionToken{
    private int positionToAdd;

    public BlackCrossActionToken(int positionToAdd) {
        this.positionToAdd = positionToAdd;
    }

    @Override
    public boolean activate(Game game) {
        switch(positionToAdd){
            case 1:{ //Shuffle
                game.getVaticanRoute().getTokenList().get(1).advance();
                return true;
            }
            case 2:{ //Do not shuffle
                game.getVaticanRoute().getTokenList().get(1).advance(2);
                return false;
            }
            default:
                return false;    
        }
    }
}

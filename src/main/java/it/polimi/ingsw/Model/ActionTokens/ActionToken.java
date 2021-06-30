package it.polimi.ingsw.Model.ActionTokens;

import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.VaticanRoute.VaticanRoute;


/**
 * Represent an action token for single player games.
 */
public interface ActionToken {

    /**
     * Execute actions on the game
     */
    void activate();


}

package it.polimi.ingsw.Model.ActionTokens;

import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.VaticanRoute.VaticanRoute;

public interface ActionToken {
    public boolean activate(Game game);
}

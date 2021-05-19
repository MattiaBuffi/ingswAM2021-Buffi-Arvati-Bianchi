
package it.polimi.ingsw.Model.ActionTokens;


import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.ProductionCard.DevelopmentCardColor;
import it.polimi.ingsw.Model.VaticanRoute.VaticanRoute;

public class DiscardActionToken implements ActionToken{
    private DevelopmentCardColor color;

    public DiscardActionToken(DevelopmentCardColor color) {
        this.color = color;
    }

    @Override
    public boolean activate(Game game) {
        //DISCARD 2 DEVELOPMENT CARDS
        return false;
    }
}

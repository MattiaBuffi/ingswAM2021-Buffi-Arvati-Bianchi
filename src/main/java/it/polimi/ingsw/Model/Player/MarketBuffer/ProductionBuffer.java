package it.polimi.ingsw.Model.Player.MarketBuffer;

import it.polimi.ingsw.Model.Marble.RedMarble;
import it.polimi.ingsw.Model.Marble.ResourceMarble;
import it.polimi.ingsw.Model.Marble.WhiteMarble;

public class ProductionBuffer extends PlayerMarbleHandler {

    @Override
    protected void handleRed(RedMarble marble) {
        player.faithToken.advance();
    }

    @Override
    protected void handleWhite(WhiteMarble marble) {
        return;
    }

    @Override
    protected void handleResource(ResourceMarble marble) {
        buffer.add(marble);
    }

    @Override
    public void empty() {
        player.resourceStorage.deposit(buffer);
    }

}

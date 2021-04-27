package it.polimi.ingsw.Model.Player.MarketBuffer;

import it.polimi.ingsw.Model.Marble.*;

public class MarketBuffer extends PlayerMarbleHandler {


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
        for (int i = 0; i < buffer.getSize(); i++) {
            player.faithToken.concede();
        }
    }

}

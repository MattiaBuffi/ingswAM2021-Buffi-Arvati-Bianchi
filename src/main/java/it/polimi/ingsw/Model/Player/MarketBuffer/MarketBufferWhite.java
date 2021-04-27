package it.polimi.ingsw.Model.Player.MarketBuffer;

import it.polimi.ingsw.Model.Marble.WhiteMarble;

public class MarketBufferWhite extends MarketBuffer {

    @Override
    protected void handleWhite(WhiteMarble marble) {
        buffer.add(marble);
    }

}

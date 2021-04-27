package it.polimi.ingsw.Model.LeaderCard;

import it.polimi.ingsw.Model.Marble.MarbleColor;
import it.polimi.ingsw.Model.Player.MarketBuffer.MarketBufferWhite;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.ActionStrategy.StoreResourceDecorated;



public class ActivateWhiteMarbles implements ActivationStrategy {

    private final MarbleColor color;

    public ActivateWhiteMarbles(MarbleColor color) {
        this.color = color;
    }

    @Override
    public void execute(Player player) {
        player.marketBuffer = new MarketBufferWhite();
        player.takeResourceStrategy = new StoreResourceDecorated(player.takeResourceStrategy, this.color);
    }

}

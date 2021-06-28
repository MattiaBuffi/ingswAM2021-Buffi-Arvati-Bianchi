package it.polimi.ingsw.Model.LeaderCard.ActivationStrategy;

import it.polimi.ingsw.Model.LeaderCard.LeaderCard;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.ResourceMarket.WhiteMarbleConversion;

public class WhiteMarble implements ActivationStrategy {

    private final Marble.Color color;

    public WhiteMarble(Marble.Color color) {
        this.color = color;
    }

    @Override
    public Marble.Color getColor() {
        return color;
    }

    @Override
    public boolean activate(Player player, LeaderCard card){
        player.setResourceMarketBuffer(new WhiteMarbleConversion(player.getResourceMarketBuffer(), color));
        return true;
    }

}

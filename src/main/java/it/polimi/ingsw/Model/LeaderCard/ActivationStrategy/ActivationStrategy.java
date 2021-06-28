package it.polimi.ingsw.Model.LeaderCard.ActivationStrategy;

import it.polimi.ingsw.Model.LeaderCard.LeaderCard;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Player.Player;


public interface ActivationStrategy {

    enum Type{
        DISCOUNT,
        EXTRA_PRODUCTION,
        EXTRA_SHELF,
        MARBLE_CONVERSION;
    }

    Marble.Color getColor();

    boolean activate(Player player, LeaderCard card);



}

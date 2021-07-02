package it.polimi.ingsw.Model.LeaderCard.ActivationStrategy;

import it.polimi.ingsw.Model.LeaderCard.LeaderCard;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Player.Player;


/**
 *  Apply changes of a Leader Card
 */
public interface ActivationStrategy {

    enum Type{
        DISCOUNT,
        EXTRA_PRODUCTION,
        EXTRA_SHELF,
        MARBLE_CONVERSION;
    }

    Marble.Color getColor();

    Type getType();

    boolean activate(Player player, LeaderCard card);



}

package it.polimi.ingsw.Model.LeaderCard.ActivationStrategy;

import it.polimi.ingsw.Model.LeaderCard.LeaderCard;
import it.polimi.ingsw.Model.Player.Player;


public interface ActivationStrategy {

    boolean activate(Player player, LeaderCard card);

}

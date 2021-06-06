package it.polimi.ingsw.Model.LeaderCard.RequirementsStrategy;

import it.polimi.ingsw.Model.Player.Player;

public interface RequirementStrategy {

    boolean canActivate(Player player);

}

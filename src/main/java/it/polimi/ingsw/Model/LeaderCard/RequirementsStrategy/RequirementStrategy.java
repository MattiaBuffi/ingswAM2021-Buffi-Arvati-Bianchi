package it.polimi.ingsw.Model.LeaderCard.RequirementsStrategy;

import it.polimi.ingsw.Model.Player.Player;

/**
 * Interface for handling the different type of Requirement of a Leader Card
 */

public interface RequirementStrategy {

    enum Type{
        CARD,
        RESOURCE;
    }

    /**
     * Check if the player can activate a Leader Card
     * @param player Player who wants to activate a Leader Card
     * @return True if the player can Activate a Leader Card
     */
    boolean canActivate(Player player);

    /**
     * @return Requirement Type of a Leader Card
     */
    Type getType();

}

package it.polimi.ingsw.Model.LeaderCard.RequirementsStrategy;

import it.polimi.ingsw.Model.Player.Player;

public interface RequirementStrategy {

    enum Type{
        CARD,
        RESOURCE;
    }

    boolean canActivate(Player player);

    Type getType();

}

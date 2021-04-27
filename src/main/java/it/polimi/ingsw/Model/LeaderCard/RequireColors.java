package it.polimi.ingsw.Model.LeaderCard;

import it.polimi.ingsw.Model.Player.Player;

public class RequireColors implements RequirementStrategy {
    @Override
    public boolean checkRequirements(Player player) {
        return false;
    }

}

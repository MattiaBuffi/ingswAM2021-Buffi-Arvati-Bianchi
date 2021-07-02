package it.polimi.ingsw.Model.LeaderCard.RequirementsStrategy;

import it.polimi.ingsw.Model.Marble.ResourceList;
import it.polimi.ingsw.Model.Player.Player;
/**
 *  Check if a Player has accomplished ResourcesRequirement to activate a Leader Card
 */
public class RequireResources implements RequirementStrategy {

    private ResourceList resource;

    public RequireResources(ResourceList resource) {
        this.resource = resource;
    }

    public ResourceList getResource() {
        return resource;
    }


    /**
     * Check if the player can activate a Leader Card
     * @param player Player who wants to activate a Leader Card
     * @return True if the player can Activate a Leader Card
     */
    @Override
    public boolean canActivate(Player player) {

        if(!player.getResourceStorage().getResources().contains(resource)){
            return false;
        }

        return true;
    }

    /**
     * @return Requirement Type of a Leader Card
     */
    @Override
    public Type getType() {
        return Type.RESOURCE;
    }

}
